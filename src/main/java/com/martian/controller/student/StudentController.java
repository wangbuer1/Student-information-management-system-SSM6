package com.martian.controller.student;

import com.martian.common.cache.SessionCache;
import com.martian.common.page.PageVo;
import com.martian.common.result.JSONResult;
import com.martian.common.util.JsonUtils;
import com.martian.common.util.RandomUtils;
import com.martian.controller.base.BaseController;
import com.martian.dto.student.StudentDto;
import com.martian.dto.student.StudentSearchDto;
import com.martian.entity.user.User;
import com.martian.enums.SexEnum;
import com.martian.enums.course.TermEnum;
import com.martian.enums.role.RoleEnum;
import com.martian.service.depart.IDepartService;
import com.martian.service.student.IStudentService;
import com.martian.service.teacher.ITeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 学生控制器
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IDepartService departService;

    @Autowired
    private ITeacherService teacherService;

    /**
     * 进入学生列表页面
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        LOGGER.debug("into student/list");
        model.addAttribute("sexEnum", SexEnum.values());
        model.addAttribute("departList", departService.getDepartList());
        return "student/list";
    }

    /**
     * 获取学号
     *
     * @return
     */
    @RequestMapping(value = "/getStuNo", method = RequestMethod.GET)
    @ResponseBody
    public Object getStuNo() {
        LOGGER.debug("into student/getStuNo");
        return new JSONResult(RandomUtils.getRandomNumber());
    }

    /**
     * 搜索学生
     *
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Object search(StudentSearchDto studentSearchDto, HttpServletRequest request) {
        LOGGER.debug("into student/search studentSearchDto:{}", JsonUtils.toJSONString(studentSearchDto));
        User user = SessionCache.getUser(request).getUser();
        if (RoleEnum.TEACHER.code.equals(user.getRoleId())) {
            studentSearchDto.setTeacherId(user.getObjId());
        }
        PageVo pageVo = studentService.searchStudentVoPage(studentSearchDto);
        return new JSONResult(pageVo);
    }
    /**
     * 进入成绩列表页面
     *
     * @return
     */
    @RequestMapping(value = "/goSearchScore", method = RequestMethod.GET)
    public String goSearchScore(Model model) {
        LOGGER.debug("into student/goSearchScore");
        model.addAttribute("departList", departService.getDepartList());
        model.addAttribute("teacherList", teacherService.getTeacherList());
        model.addAttribute("termEnum", TermEnum.values());
        return "score/stuSearchScore";
    }
    /**
     * 进入修改个人信息页面
     *
     * @return
     */
    @RequestMapping(value = "/goUpdatePersonInfo", method = RequestMethod.GET)
    public String goUpdatePersonInfo(Model model, HttpServletRequest request) {
        LOGGER.debug("into student/goUpdatePersonInfo");
        String studentId = SessionCache.getUser(request).getUser().getObjId();
        model.addAttribute("student", studentService.getStudentVo(studentId));
        model.addAttribute("sexEnum", SexEnum.values());
        return "student/personInfo";
    }

    /**
     * 修改个人信息
     *
     * @return
     */
    @RequestMapping(value = "/updatePersonInfo", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult updatePersonInfo(StudentDto studentDto) {
        LOGGER.debug("into student/updatePersonInfo");
        studentService.updatePersonInfo(studentDto);
        return new JSONResult();
    }

    /**
     * 添加学生
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(StudentDto studentDto) {
        LOGGER.debug("into student/add studentDto:{}", JsonUtils.toJSONString(studentDto));
        studentService.add(studentDto);
        return new JSONResult();
    }


    /**
     * 修改学生
     *
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(StudentDto studentDto) {
        LOGGER.debug("into student/update studentDto:{}", JsonUtils.toJSONString(studentDto));
        studentService.update(studentDto);
        return new JSONResult();
    }

    /**
     * 获取学生详情
     *
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(String studentId) {
        LOGGER.debug("into student/detail?studentId={}", studentId);
        return new JSONResult(studentService.getStudentVo(studentId));
    }

    /**
     * 删除学生
     *
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Object del(String studentId) {
        LOGGER.debug("into student/del studentId:{}", studentId);
        studentService.del(studentId);
        return new JSONResult();
    }
}
