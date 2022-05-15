package com.martian.controller;

import com.martian.common.cache.SessionCache;
import com.martian.common.constant.Constant;
import com.martian.common.result.JSONResult;
import com.martian.common.util.JsonUtils;
import com.martian.common.util.CommonUtils;
import com.martian.controller.base.BaseController;
import com.martian.dto.user.LoginDto;
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
 * 登录控制器
 */
@Controller
public class LoginController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private IUserService userService;

    @RequestMapping("/")
    public String index() {
        return "borrow/list";
    }

    /**
     * 进入登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request) {
        logger.info("into /loginPage >>>>>>>>>>>>>>>>>>>>>>>");

        //判断当前用户是否登录,如果已登录转到系统首页
        LoginVo user = SessionCache.getUser(request);
        if (CommonUtils.isNotEmpty(user)) {
            return "redirect:/";
        }
        return "login";
    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(LoginDto loginDto, HttpServletRequest request) {
        logger.info("into /login loginDto:{}", JsonUtils.toJSONString(loginDto));
        //用户登录信息
        LoginVo loginVo = userService.login(loginDto);

        //将用户信息存放在session中
        SessionCache.setUser(request, loginVo);
        request.getSession().setAttribute(Constant.SESSION_USER, loginVo);
        return new JSONResult();
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Object logout(HttpServletRequest request) {
        logger.info("into /logout >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        SessionCache.removeUser(request);
        return "redirect:/login";
    }

}
