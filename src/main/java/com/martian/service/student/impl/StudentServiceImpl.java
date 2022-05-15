package com.martian.service.student.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.martian.common.exception.BusinessAsserts;
import com.martian.common.exception.Exceptions;
import com.martian.common.page.PageVo;
import com.martian.common.util.CommonUtils;
import com.martian.common.util.EntityUtils;
import com.martian.common.util.JsonUtils;
import com.martian.dao.student.StudentMapper;
import com.martian.dto.student.StudentDto;
import com.martian.dto.student.StudentSearchDto;
import com.martian.entity.clazz.Clazz;
import com.martian.entity.student.Student;
import com.martian.entity.user.User;
import com.martian.enums.YNEnum;
import com.martian.enums.role.RoleEnum;
import com.martian.service.clazz.IClazzService;
import com.martian.service.student.IStudentService;
import com.martian.service.user.IUserService;
import com.martian.vo.student.StudentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 学生表 服务实现类
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    private IUserService userService;

    @Autowired
    private IClazzService clazzService;

    /**
     * 搜索
     *
     * @param studentSearchDto
     * @return
     */
    @Override
    public PageVo searchStudentVoPage(StudentSearchDto studentSearchDto) {
        Page<StudentVo> page = new Page<>(studentSearchDto.getPageNo(), studentSearchDto.getPageSize());
        page.setRecords(this.baseMapper.searchStudentVoPage(page, studentSearchDto));
        return new PageVo(page);
    }

    /**
     * 添加
     *
     * @param studentDto
     */
    @Override
    @Transactional
    public void add(StudentDto studentDto) {
        //校验
        BusinessAsserts.notBlank(studentDto.getUserName(), Exceptions.User.USERNAME_NULL);
        BusinessAsserts.notBlank(studentDto.getName(), Exceptions.Student.STUDENT_NAME_CAN_NOT_NULL);
        User user = userService.getUserByUserName(studentDto.getUserName());
        BusinessAsserts.isNull(user, Exceptions.User.USERNAME_HAS_USED);
        BusinessAsserts.notBlank(studentDto.getClazzId(), Exceptions.Clazz.CLAZZ_ID_NULL);

        //添加学生
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        EntityUtils.init(student);

        Clazz clazz = clazzService.selectById(studentDto.getClazzId());
        student.setDepartId(clazz.getDepartId());

        boolean flag = super.insert(student);
        if (!flag) {
            LOGGER.error("学生添加失败，student:{}", JsonUtils.toJSONString(student));
        }
        BusinessAsserts.isTrue(flag, Exceptions.System.ADD_FAIL);

        //添加用户
        userService.add(studentDto.getUserName(), studentDto.getPassword(), RoleEnum.STUDENT, student.getId());
    }

    /**
     * 修改
     *
     * @param studentDto
     */
    @Override
    @Transactional
    public void update(StudentDto studentDto) {
        //校验
        BusinessAsserts.notBlank(studentDto.getId(), Exceptions.Student.STUDENT_ID_NULL);
        BusinessAsserts.notBlank(studentDto.getClazzId(), Exceptions.Clazz.CLAZZ_ID_NULL);
        //修改
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        EntityUtils.init(student);
        Clazz clazz = clazzService.selectById(studentDto.getClazzId());
        student.setDepartId(clazz.getDepartId());

        boolean flag = super.updateById(student);
        if (!flag) {
            LOGGER.error("学生修改失败，student:{}", JsonUtils.toJSONString(student));
        }
        BusinessAsserts.isTrue(flag, Exceptions.System.UPDATE_FAIL);

        //修改密码
        if (CommonUtils.isNotEmpty(studentDto.getPassword())) {
            userService.updatePwd(RoleEnum.STUDENT, studentDto.getId(), studentDto.getPassword());
        }
    }

    /**
     * 删除
     *
     * @param studentId
     */
    @Override
    public void del(String studentId) {
        if (CommonUtils.isNotEmpty(studentId)) {
            Student param = new Student();
            param.setId(studentId);
            param.setIsValid(YNEnum.NO);
            EntityUtils.init(param);
            boolean flag = super.updateById(param);
            if (!flag) {
                LOGGER.error("学生删除失败，param:{}", JsonUtils.toJSONString(param));
            }
            BusinessAsserts.isTrue(flag, Exceptions.System.DEL_FAIL);
            userService.del(RoleEnum.STUDENT, studentId);
        }
    }

    /**
     * 获取学生信息
     *
     * @param studentId
     * @return
     */
    @Override
    public StudentVo getStudentVo(String studentId) {
        return this.baseMapper.getStudentVo(studentId);
    }

    /**
     * 修改个人信息
     *
     * @param studentDto
     */
    @Override
    public void updatePersonInfo(StudentDto studentDto) {
        //校验
        BusinessAsserts.notBlank(studentDto.getId(), Exceptions.Student.STUDENT_ID_NULL);
        //修改
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        EntityUtils.init(student);

        boolean flag = super.updateById(student);
        if (!flag) {
            LOGGER.error("个人信息修改失败，student:{}", JsonUtils.toJSONString(student));
        }
        BusinessAsserts.isTrue(flag, Exceptions.System.UPDATE_FAIL);
    }
}
