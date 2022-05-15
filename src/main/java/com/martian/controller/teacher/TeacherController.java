package com.martian.controller.teacher;

import com.martian.common.cache.SessionCache;
import com.martian.common.page.PageVo;
import com.martian.common.result.JSONResult;
import com.martian.common.util.JsonUtils;
import com.martian.controller.base.BaseController;
import com.martian.dto.teacher.ScoreTjSearchDto;
import com.martian.dto.teacher.TeacherDto;
import com.martian.dto.teacher.TeacherSearchDto;
import com.martian.entity.clazz.Clazz;
import com.martian.entity.course.Course;
import com.martian.entity.student.Student;
import com.martian.entity.teacher.Teacher;
import com.martian.enums.SexEnum;
import com.martian.enums.course.TermEnum;
import com.martian.service.clazz.IClazzService;
import com.martian.service.course.ICourseService;
import com.martian.service.depart.IDepartService;
import com.martian.service.teacher.ITeacherService;
import com.martian.vo.course.CourseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教师控制器
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    private IDepartService departService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IClazzService clazzService;

    @Autowired
    private ICourseService courseService;

    /**
     * 进入教师列表页面
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        LOGGER.debug("into teacher/list");
        model.addAttribute("sexEnum", SexEnum.values());
        return "teacher/list";
    }

    /**
     * 进入修改个人信息页面
     *
     * @return
     */
    @RequestMapping(value = "/goUpdatePersonInfo", method = RequestMethod.GET)
    public String goUpdatePersonInfo(Model model, HttpServletRequest request) {
        LOGGER.debug("into teacher/goUpdatePersonInfo");
        Teacher teacher = teacherService.selectById(SessionCache.getUser(request).getUser().getObjId());
        model.addAttribute("teacher", teacher);
        model.addAttribute("sexEnum", SexEnum.values());
        return "teacher/personInfo";
    }

    /**
     * 搜索教师
     *
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Object search(TeacherSearchDto teacherSearchDto) {
        LOGGER.debug("into teacher/search teacherSearchDto:{}", JsonUtils.toJSONString(teacherSearchDto));
        PageVo pageVo = teacherService.searchTeacherVoPage(teacherSearchDto);
        return new JSONResult(pageVo);
    }

    /**
     * 进入查询学生页面
     *
     * @return
     */
    @RequestMapping(value = "/goSearchStudent", method = RequestMethod.GET)
    public String goSearchStudent(Model model, HttpServletRequest request) {
        LOGGER.debug("into teacher/goSearchStudent");
        String teacherId = SessionCache.getUser(request).getUser().getObjId();
        //获取教师授课班级
        List<Clazz> clazzList = clazzService.getTeacherClazzList(teacherId);
        model.addAttribute("clazzList", clazzList);
        model.addAttribute("sexEnum", SexEnum.values());
        model.addAttribute("departList", departService.getDepartList());
        return "teacher/searchStudent";
    }
    /**
     * 进入教师成绩统计列表页面
     *
     * @return
     */
    @RequestMapping(value = "/goTjPage", method = RequestMethod.GET)
    public String tjPage(Model model,HttpServletRequest request) {
        LOGGER.debug("into teacher/goTjPage");
        String teacherId = SessionCache.getUser(request).getUser().getObjId();
        //获取教师授课班级
        List<Clazz> clazzList = clazzService.getTeacherClazzList(teacherId);
        model.addAttribute("clazzList", clazzList);
        return "teacher/teacherTj";
    }

    /**
     * 根据老师ID和班级ID查询课程列表
     *
     * @return
     */
    @RequestMapping(value = "/getCourseList", method = RequestMethod.GET)
    @ResponseBody
    public Object getCourseList(HttpServletRequest request,String clazzId) {
        LOGGER.debug("into teacher/getCourseList");
        String teacherId = SessionCache.getUser(request).getUser().getObjId();
        List<Course> courseList = courseService.getTeacherCourseList(teacherId,clazzId);
        return new JSONResult(courseList);
    }
    /**
     * 成绩统计
     *
     * @return
     */
    @RequestMapping(value = "/scoreTjSearch", method = RequestMethod.GET)
    @ResponseBody
    public Object scoreTjSearch(HttpServletRequest request,ScoreTjSearchDto scoreTjSearchDto) {
        LOGGER.debug("into teacher/scoreTjSearch scoreTjSearchDto:{}", JsonUtils.toJSONString(scoreTjSearchDto));
        String teacherId = SessionCache.getUser(request).getUser().getObjId();
        scoreTjSearchDto.setTeacherId(teacherId);
        PageVo pageVo = teacherService.scoreTjSearch(scoreTjSearchDto);
        return new JSONResult(pageVo);
    }
    /**
     * 进入成绩列表页面
     *
     * @return
     */
    @RequestMapping(value = "/goSearchScore", method = RequestMethod.GET)
    public String goSearchScore(Model model, HttpServletRequest request) {
        LOGGER.debug("into score/goSearchScore");
        String teacherId = SessionCache.getUser(request).getUser().getObjId();
        //获取教师授课班级
        List<Clazz> clazzList = clazzService.getTeacherClazzList(teacherId);
        List<Course> courseList = courseService.getTeacherCourseList(teacherId);
        model.addAttribute("clazzList", clazzList);
        model.addAttribute("courseList", courseList);
        model.addAttribute("departList", departService.getDepartList());
        model.addAttribute("termEnum", TermEnum.values());
        return "score/teacherSearchScore";
    }

    /**
     * 获取教师授课的课程
     *
     * @return
     */
    @RequestMapping(value = "/getCourse/{clazzId}", method = RequestMethod.GET)
    @ResponseBody
    public Object getCourse(@PathVariable String clazzId, HttpServletRequest request) {
        LOGGER.debug("into teacher/getCourse/{}", JsonUtils.toJSONString(clazzId));
        String teacherId = SessionCache.getUser(request).getUser().getObjId();
        List<Course> courseList = teacherService.getCourse(teacherId, clazzId);
        return new JSONResult(courseList);
    }

    /**
     * 获取学生
     *
     * @return
     */
    @RequestMapping(value = "/getStudentAndCourse", method = RequestMethod.GET)
    @ResponseBody
    public Object getStudentByCourseId(String courseId, HttpServletRequest request) {
        LOGGER.debug("into teacher/getStudentAndCourse?courseId={}", courseId);
        List<Student> studentList = teacherService.getStudentByCourseId(courseId);
        CourseVo courseVo = courseService.getCourseVo(courseId);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("course", courseVo);
        resMap.put("studentList", studentList);
        return new JSONResult(resMap);
    }

    /**
     * 添加教师
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(TeacherDto teacherDto) {
        LOGGER.debug("into teacher/add teacherDto:{}", JsonUtils.toJSONString(teacherDto));
        teacherService.add(teacherDto);
        return new JSONResult();
    }


    /**
     * 修改教师
     *
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(TeacherDto teacherDto) {
        LOGGER.debug("into teacher/update teacherDto:{}", JsonUtils.toJSONString(teacherDto));
        teacherService.update(teacherDto);
        return new JSONResult();
    }

    /**
     * 获取教师详情
     *
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(String teacherId) {
        LOGGER.debug("into teacher/detail?teacherId={}", teacherId);
        return new JSONResult(teacherService.getTeacherVo(teacherId));
    }

    /**
     * 删除教师
     *
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Object del(String teacherId) {
        LOGGER.debug("into teacher/del teacherId:{}", teacherId);
        teacherService.del(teacherId);
        return new JSONResult();
    }
}
