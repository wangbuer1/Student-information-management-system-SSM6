package com.martian.controller.score;

import com.martian.common.cache.SessionCache;
import com.martian.common.page.PageVo;
import com.martian.common.result.JSONResult;
import com.martian.common.util.JsonUtils;
import com.martian.controller.base.BaseController;
import com.martian.dto.score.ScoreDto;
import com.martian.dto.score.ScoreSearchDto;
import com.martian.entity.user.User;
import com.martian.enums.course.TermEnum;
import com.martian.enums.role.RoleEnum;
import com.martian.service.depart.IDepartService;
import com.martian.service.score.IScoreService;
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
 * 成绩控制器
 */
@Controller
@RequestMapping("/score")
public class ScoreController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreController.class);

    @Autowired
    private IDepartService departService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IScoreService scoreService;

    /**
     * 进入成绩列表页面
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        LOGGER.debug("into score/list");
        model.addAttribute("departList", departService.getDepartList());
        model.addAttribute("teacherList", teacherService.getTeacherList());
        model.addAttribute("termEnum", TermEnum.values());
        return "score/list";
    }

    /**
     * 搜索成绩
     *
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Object search(ScoreSearchDto scoreSearchDto, HttpServletRequest request) {
        LOGGER.debug("into score/search courseSearchDto:{}", JsonUtils.toJSONString(scoreSearchDto));
        User user = SessionCache.getUser(request).getUser();
        if (RoleEnum.TEACHER.code.equals(user.getRoleId())) {
            scoreSearchDto.setTeacherId(user.getObjId());
        }else if (RoleEnum.STUDENT.code.equals(user.getRoleId())){
            scoreSearchDto.setStudentId(user.getObjId());
        }
        PageVo pageVo = scoreService.searchScoreVoPage(scoreSearchDto);
        return new JSONResult(pageVo);
    }

    /**
     * 添加成绩
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ScoreDto scoreDto) {
        LOGGER.debug("into score/add courseDto:{}", JsonUtils.toJSONString(scoreDto));
        scoreService.add(scoreDto);
        return new JSONResult();
    }


    /**
     * 修改成绩
     *
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(ScoreDto scoreDto) {
        LOGGER.debug("into score/update scoreDto:{}", JsonUtils.toJSONString(scoreDto));
        scoreService.update(scoreDto);
        return new JSONResult();
    }
}
