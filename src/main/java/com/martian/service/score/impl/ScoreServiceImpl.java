package com.martian.service.score.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.martian.common.exception.BusinessAsserts;
import com.martian.common.exception.Exceptions;
import com.martian.common.page.PageVo;
import com.martian.common.util.EntityUtils;
import com.martian.common.util.JsonUtils;
import com.martian.dao.score.ScoreMapper;
import com.martian.dto.score.ScoreDto;
import com.martian.dto.score.ScoreSearchDto;
import com.martian.entity.course.Course;
import com.martian.entity.score.Score;
import com.martian.entity.student.Student;
import com.martian.service.course.ICourseService;
import com.martian.service.score.IScoreService;
import com.martian.service.student.IStudentService;
import com.martian.vo.score.ScoreVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 成绩表 服务实现类
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreServiceImpl.class);
    @Autowired
    private ICourseService courseService;
    @Autowired
    private IStudentService studentService;

    /**
     * 搜索
     *
     * @param scoreSearchDto
     * @return
     */
    @Override
    public PageVo searchScoreVoPage(ScoreSearchDto scoreSearchDto) {
        Page<ScoreVo> page = new Page<>(scoreSearchDto.getPageNo(), scoreSearchDto.getPageSize());
        page.setRecords(this.baseMapper.searchScoreVoPage(page, scoreSearchDto));
        return new PageVo(page);
    }

    /**
     * 添加
     *
     * @param scoreDto
     */
    @Override
    public void add(ScoreDto scoreDto) {
        //校验
        BusinessAsserts.notBlank(scoreDto.getStudentId(), Exceptions.Student.STUDENT_ID_NULL);
        BusinessAsserts.notBlank(scoreDto.getCourseId(), Exceptions.Course.COURSE_ID_NULL);
        Student student = studentService.selectById(scoreDto.getStudentId());
        BusinessAsserts.notNull(student, Exceptions.Student.STUDENT_NOT_EXIST);
        Course course = courseService.selectById(scoreDto.getCourseId());
        BusinessAsserts.notNull(course, Exceptions.Course.COURSE_NOT_EXIST);

        //添加
        Score score = new Score();
        EntityUtils.init(score);
        score.setDepartId(course.getDepartId());
        score.setClazzId(course.getClazzId());
        score.setStudentId(scoreDto.getStudentId());
        score.setCourseId(scoreDto.getCourseId());
        score.setYear(course.getYear());
        score.setTerm(course.getTerm().code);
        score.setScore(scoreDto.getScore());
        boolean flag = super.insert(score);
        if (!flag) {
            LOGGER.error("成绩添加失败，clazz:{}", JsonUtils.toJSONString(score));
        }
        BusinessAsserts.isTrue(flag, Exceptions.System.ADD_FAIL);
    }

    /**
     * 修改
     *
     * @param scoreDto
     */
    @Override
    public void update(ScoreDto scoreDto) {
        //校验
        BusinessAsserts.notBlank(scoreDto.getId(), Exceptions.Student.STUDENT_SCORE_NOT_EXIST);
        //修改
        Score score = new Score();
        score.setId(scoreDto.getId());
        EntityUtils.init(score);
        score.setScore(scoreDto.getScore());
        boolean flag = super.updateById(score);
        if (!flag) {
            LOGGER.error("成绩修改失败，clazz:{}", JsonUtils.toJSONString(score));
        }
        BusinessAsserts.isTrue(flag, Exceptions.System.UPDATE_FAIL);
    }
}
