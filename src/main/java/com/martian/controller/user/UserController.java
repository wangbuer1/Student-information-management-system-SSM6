package com.martian.controller.user;

import com.martian.common.constant.Constant;
import com.martian.common.result.JSONResult;
import com.martian.common.util.JsonUtils;
import com.martian.controller.base.BaseController;
import com.martian.dto.user.UpdatePwdDto;
import com.martian.service.user.IUserService;
import com.martian.vo.user.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户控制器
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    /**
     * 进入修改密码页面
     *
     * @return
     */
    @RequestMapping(value = "/goUpdatePwd", method = RequestMethod.GET)
    public String goUpdatePwd() {
        logger.debug("into user/goUpdatePwd");
        return "user/updatePwd";
    }

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    @ResponseBody
    public Object updatePwd(UpdatePwdDto updatePwdDto, HttpServletRequest request) {
        logger.debug("into user/updatePwd updatePwdDto:{}", JsonUtils.toJSONString(updatePwdDto));
        LoginVo loginVo = (LoginVo) request.getSession().getAttribute(Constant.SESSION_USER);
        userService.updatePwd(loginVo.getUser().getId(), updatePwdDto);
        return new JSONResult();
    }

}
