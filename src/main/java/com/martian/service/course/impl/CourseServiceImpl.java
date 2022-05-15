package com.martian.service.course.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.martian.common.exception.BusinessAsserts;
import com.martian.common.exception.Exceptions;
import com.martian.common.page.PageVo;
import com.martian.common.util.CommonUtils;
import com.martian.common.util.EntityUtils;
import com.martian.common.util.JsonUtils;
import com.martian.dao.course.CourseMapper;
import com.martian.dto.course.CourseDto;
import com.martian.dto.course.CourseSearchDto;
import com.martian.entity.clazz.Clazz;
import com.martian.entity.course.Course;
import com.martian.entity.depart.Depart;
import com.martian.entity.teacher.Teacher;
import com.martian.enums.YNEnum;
import com.martian.enums.course.TermEnum;
import com.martian.service.clazz.IClazzService;
import com.martian.service.course.ICourseService;
import com.martian.service.depart.IDepartService;
import com.martian.service.teacher.ITeacherService;
import com.martian.vo.course.CourseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程表 服务实现类
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);
    @Autowired
    private IDepartService departService;
    @Autowired
    private IClazzService clazzService;
    @Autowired
    private ITeacherService teacherService;

    /**
     * 添加
     *
     * @param courseDto
     */
    @Override
    public void add(CourseDto courseDto) {
        //校验
        BusinessAsserts.notBlank(courseDto.getYear(), Exceptions.Course.COURSE_YEAR_CAN_NOT_NULL);
        BusinessAsserts.notNull(courseDto.getTerm(), Exceptions.Course.COURSE_TERM_CAN_NOT_NULL);
        BusinessAsserts.notBlank(courseDto.getName(), Exceptions.Course.COURSE_NAME_CAN_NOT_NULL);
        BusinessAsserts.notBlank(courseDto.getClazzId(), Exceptions.Clazz.CLAZZ_ID_NULL);
        //课程
        Clazz clazz = clazzService.selectById(courseDto.getClazzId());
        BusinessAsserts.notNull(clazz, Exceptions.Clazz.CLAZZ_NOT_EXIST);
        BusinessAsserts.isTrue(YNEnum.YES.equals(clazz.getIsValid()), Exceptions.Clazz.CLAZZ_NOT_EXIST);
        //院系
        Depart depart = departService.selectById(clazz.getDepartId());
        BusinessAsserts.notNull(depart, Exceptions.Depart.DEPART_NOT_EXIST);
        BusinessAsserts.isTrue(YNEnum.YES.equals(depart.getIsValid()), Exceptions.Depart.DEPART_NOT_EXIST);
        //教师
        BusinessAsserts.notBlank(courseDto.getTeacherId(), Exceptions.Teacher.TEACHER_ID_NULL);
        Teacher teacher = teacherService.selectById(courseDto.getTeacherId());
        BusinessAsserts.notNull(teacher, Exceptions.Teacher.TEACHER_NOT_EXIST);
        //校验课程是否已存在
        BusinessAsserts.isNull(getCourse(courseDto.getName(), courseDto.getYear(), courseDto.getTerm(), courseDto.getClazzId()), Exceptions.Course.COURSE_HAS_EXISTED);
        //添加
        Course course = new Course();
        BeanUtils.copyProperties(courseDto, course);
        EntityUtils.init(course);
        course.setDepartId(depart.getId());
        boolean flag = super.insert(course);
        if (!flag) {
            LOGGER.error("课程添加失败，course:{}", JsonUtils.toJSONString(course));
        }
        BusinessAsserts.isTrue(flag, Exceptions.System.ADD_FAIL);
    }

    /**
     * 校验课程是否已存在
     *
     * @return
     */
    private Course getCourse(String name, String year, TermEnum term, String clazzId) {
        Course param = new Course();
        param.setName(name);
        param.setYear(year);
        param.setTerm(term);
        param.setClazzId(clazzId);
        return super.selectOne(new EntityWrapper<Course>(param));
    }

    /**
     * 修改
     *
     * @param courseDto
     */
    @Override
    public void update(CourseDto courseDto) {
        //校验
        BusinessAsserts.notBlank(courseDto.getId(), Exceptions.Course.COURSE_ID_NULL);
        BusinessAsserts.notBlank(courseDto.getName(), Exceptions.Course.COURSE_NAME_CAN_NOT_NULL);
        Course course = super.selectById(courseDto.getId());
        BusinessAsserts.notNull(course, Exceptions.Course.COURSE_NOT_EXIST);

        if (!courseDto.getName().equals(course.getName())) {
            //校验课程是否已存在
            BusinessAsserts.isNull(getCourse(courseDto.getName(), course.getYear(), course.getTerm(), course.getClazzId()), Exceptions.Course.COURSE_HAS_EXISTED);
        }
        //修改
        Course param = new Course();
        BeanUtils.copyProperties(courseDto, param);
        EntityUtils.init(param);
        boolean flag = super.updateById(param);
        if (!flag) {
            LOGGER.error("课程修改失败，course:{}", JsonUtils.toJSONString(param));
        }
        BusinessAsserts.isTrue(flag, Exceptions.System.UPDATE_FAIL);
    }

    /**
     * 删除
     *
     * @param courseId
     */
    @Override
    public void del(String courseId) {
        if (CommonUtils.isNotEmpty(courseId)) {
            Course param = new Course();
            param.setId(courseId);
            param.setIsValid(YNEnum.NO);
            EntityUtils.init(param);
            boolean flag = super.updateById(param);
            if (!flag) {
                LOGGER.error("课程删除失败，param:{}", JsonUtils.toJSONString(param));
            }
            BusinessAsserts.isTrue(flag, Exceptions.System.DEL_FAIL);
        }
    }

    @Override
    public PageVo searchCourseVoPage(CourseSearchDto courseSearchDto) {
        Page<CourseVo> page = new Page<>(courseSearchDto.getPageNo(), courseSearchDto.getPageSize());
        page.setRecords(this.baseMapper.searchCourseVoPage(page, courseSearchDto));
        return new PageVo(page);
    }

    @Override
    public CourseVo getCourseVo(String courseId) {
        return this.baseMapper.getCourseVo(courseId);
    }

    /**
     * 查询老师教授的课程
     *
     * @param teacherId
     * @return
     */
    @Override
    public List<Course> getTeacherCourseList(String teacherId) {
        Course param = new Course();
        param.setIsValid(YNEnum.YES);
        param.setTeacherId(teacherId);
        return super.selectList(new EntityWrapper<>(param));
    }

    @Override
    public List<Course> getTeacherCourseList(String teacherId, String clazzId) {
        Course param = new Course();
        param.setIsValid(YNEnum.YES);
        param.setTeacherId(teacherId);
        param.setClazzId(clazzId);
        return super.selectList(new EntityWrapper<>(param));
    }
}
