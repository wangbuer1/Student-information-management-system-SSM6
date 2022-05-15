package com.martian.controller.course;

import com.martian.common.page.PageVo;
import com.martian.common.result.JSONResult;
import com.martian.common.util.JsonUtils;
import com.martian.controller.base.BaseController;
import com.martian.dto.course.CourseDto;
import com.martian.dto.course.CourseSearchDto;
import com.martian.enums.course.TermEnum;
import com.martian.service.course.ICourseService;
import com.martian.service.depart.IDepartService;
import com.martian.service.teacher.ITeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 课程控制器
 */
@Controller
@RequestMapping("/course")
public class CourseController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IDepartService departService;

    @Autowired
    private ITeacherService teacherService;

    /**
     * 进入课程列表页面
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        LOGGER.debug("into course/list");
        model.addAttribute("departList", departService.getDepartList());
        model.addAttribute("teacherList", teacherService.getTeacherList());
        model.addAttribute("termEnum", TermEnum.values());
        return "course/list";
    }

    /**
     * 搜索课程
     *
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Object search(CourseSearchDto courseSearchDto) {
        LOGGER.debug("into course/search courseSearchDto:{}", JsonUtils.toJSONString(courseSearchDto));
        PageVo pageVo = courseService.searchCourseVoPage(courseSearchDto);
        return new JSONResult(pageVo);
    }

    /**
     * 添加课程
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(CourseDto courseDto) {
        LOGGER.debug("into course/add courseDto:{}", JsonUtils.toJSONString(courseDto));
        courseService.add(courseDto);
        return new JSONResult();
    }


    /**
     * 修改课程
     *
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(CourseDto courseDto) {
        LOGGER.debug("into course/update courseDto:{}", JsonUtils.toJSONString(courseDto));
        courseService.update(courseDto);
        return new JSONResult();
    }

    /**
     * 获取课程详情
     *
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(String courseId) {
        LOGGER.debug("into course/detail?courseId={}", courseId);
        return new JSONResult(courseService.getCourseVo(courseId));
    }

    /**
     * 删除课程
     *
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Object del(String courseId) {
        LOGGER.debug("into course/del courseId:{}", courseId);
        courseService.del(courseId);
        return new JSONResult();
    }
}
