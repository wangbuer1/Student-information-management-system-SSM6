package com.martian.service.teacher.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.martian.common.exception.BusinessAsserts;
import com.martian.common.exception.Exceptions;
import com.martian.common.page.PageVo;
import com.martian.common.util.CommonUtils;
import com.martian.common.util.EntityUtils;
import com.martian.common.util.JsonUtils;
import com.martian.dao.teacher.TeacherMapper;
import com.martian.dto.teacher.ScoreTjSearchDto;
import com.martian.dto.teacher.TeacherDto;
import com.martian.dto.teacher.TeacherSearchDto;
import com.martian.entity.course.Course;
import com.martian.entity.score.Score;
import com.martian.entity.student.Student;
import com.martian.entity.teacher.Teacher;
import com.martian.entity.user.User;
import com.martian.enums.YNEnum;
import com.martian.enums.role.RoleEnum;
import com.martian.service.score.IScoreService;
import com.martian.service.teacher.ITeacherService;
import com.martian.service.user.IUserService;
import com.martian.vo.teacher.ScoreTjVo;
import com.martian.vo.teacher.TeacherVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 教师表 服务实现类
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherServiceImpl.class);
    @Autowired
    private IUserService userService;

    @Autowired
    private IScoreService scoreService;

    /**
     * 搜索
     *
     * @param teacherSearchDto
     * @return
     */
    @Override
    public PageVo searchTeacherVoPage(TeacherSearchDto teacherSearchDto) {
        Page<TeacherVo> page = new Page<>(teacherSearchDto.getPageNo(), teacherSearchDto.getPageSize());
        page.setRecords(this.baseMapper.searchTeacherVoPage(page, teacherSearchDto));
        return new PageVo(page);
    }

    /**
     * 添加
     *
     * @param teacherDto
     */
    @Override
    @Transactional
    public void add(TeacherDto teacherDto) {
        //校验
        BusinessAsserts.notBlank(teacherDto.getUserName(), Exceptions.User.USERNAME_NULL);
        BusinessAsserts.notBlank(teacherDto.getName(), Exceptions.Teacher.TEACHER_NAME_CAN_NOT_NULL);
        User user = userService.getUserByUserName(teacherDto.getUserName());
        BusinessAsserts.isNull(user, Exceptions.User.USERNAME_HAS_USED);

        //添加教师
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherDto, teacher);
        EntityUtils.init(teacher);
        boolean flag = super.insert(teacher);
        if (!flag) {
            LOGGER.error("教师添加失败，teacher:{}", JsonUtils.toJSONString(teacher));
        }
        BusinessAsserts.isTrue(flag, Exceptions.System.ADD_FAIL);

        //添加用户
        userService.add(teacherDto.getUserName(), teacherDto.getPassword(), RoleEnum.TEACHER, teacher.getId());
    }

    /**
     * 修改
     *
     * @param teacherDto
     */
    @Override
    @Transactional
    public void update(TeacherDto teacherDto) {
        //校验
        BusinessAsserts.notBlank(teacherDto.getId(), Exceptions.Teacher.TEACHER_ID_NULL);
        //修改
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherDto, teacher);
        EntityUtils.init(teacher);
        boolean flag = super.updateById(teacher);
        if (!flag) {
            LOGGER.error("教师修改失败，teacher:{}", JsonUtils.toJSONString(teacher));
        }
        BusinessAsserts.isTrue(flag, Exceptions.System.UPDATE_FAIL);

        //修改密码
        if (CommonUtils.isNotEmpty(teacherDto.getPassword())) {
            userService.updatePwd(RoleEnum.TEACHER, teacherDto.getId(), teacherDto.getPassword());
        }
    }

    /**
     * 删除
     *
     * @param teacherId
     */
    @Override
    public void del(String teacherId) {
        if (CommonUtils.isNotEmpty(teacherId)) {
            Teacher param = new Teacher();
            param.setId(teacherId);
            param.setIsValid(YNEnum.NO);
            EntityUtils.init(param);
            boolean flag = super.updateById(param);
            if (!flag) {
                LOGGER.error("教师删除失败，param:{}", JsonUtils.toJSONString(param));
            }
            BusinessAsserts.isTrue(flag, Exceptions.System.DEL_FAIL);
            userService.del(RoleEnum.TEACHER, teacherId);
        }
    }

    /**
     * 获取教师信息
     *
     * @param teacherId
     * @return
     */
    @Override
    public TeacherVo getTeacherVo(String teacherId) {
        return this.baseMapper.getTeacherVo(teacherId);
    }

    /**
     * 获取教师列表
     *
     * @return
     */
    @Override
    public List<Teacher> getTeacherList() {
        Teacher param = new Teacher();
        param.setIsValid(YNEnum.YES);
        return super.selectList(new EntityWrapper<Teacher>(param));
    }

    /**
     * 获取教师授课的课程
     *
     * @param teacherId
     * @param clazzId
     * @return
     */
    @Override
    public List<Course> getCourse(String teacherId, String clazzId) {
        if (CommonUtils.isNotEmpty(teacherId) && CommonUtils.isNotEmpty(clazzId)) {
            return this.baseMapper.getCourse(teacherId, clazzId);
        }
        return null;
    }

    /**
     * 获取学生列表
     *
     * @param courseId
     * @return
     */
    @Override
    public List<Student> getStudentByCourseId(String courseId) {
        if (CommonUtils.isNotEmpty(courseId)) {
            return this.baseMapper.getStudentByCourseId(courseId);
        }
        return null;
    }
    /**
     * 成績统计查询
     * @param scoreTjSearchDto
     * @return
     */
    @Override
    public PageVo  scoreTjSearch(ScoreTjSearchDto scoreTjSearchDto) {
        Page<ScoreTjVo> page = new Page<>(scoreTjSearchDto.getPageNo(), scoreTjSearchDto.getPageSize());
        page.setRecords(this.baseMapper.scoreTjSearch(page, scoreTjSearchDto));
        int num60 ;
        int num6080 ;
        int num80100 ;
        for(ScoreTjVo vo: page.getRecords()){
            Score num60Param = new Score();
            num60Param.setIsValid(YNEnum.YES);
            num60Param.setClazzId(vo.getClazzId());
            num60Param.setCourseId(vo.getCourseId());
            num60 = scoreService.selectCount(new EntityWrapper<Score>(num60Param).lt("score",60));

            Score num6080Param = new Score();
            num6080Param.setIsValid(YNEnum.YES);
            num6080Param.setClazzId(vo.getClazzId());
            num6080Param.setCourseId(vo.getCourseId());
            num6080 = scoreService.selectCount(new EntityWrapper<Score>(num6080Param).lt("score",80).ge("score",60));

            Score num80100Param = new Score();
            num80100Param.setIsValid(YNEnum.YES);
            num80100Param.setClazzId(vo.getClazzId());
            num80100Param.setCourseId(vo.getCourseId());
            num80100 = scoreService.selectCount(new EntityWrapper<Score>(num80100Param).ge("score",80));

            vo.setNum60(num60);
            vo.setNum6080(num6080);
            vo.setNum80100(num80100);
        }
        return new PageVo(page);
    }
}
