package com.martian.controller.depart;

import com.martian.common.result.JSONResult;
import com.martian.common.util.JsonUtils;
import com.martian.controller.base.BaseController;
import com.martian.dto.depart.DepartDto;
import com.martian.service.depart.IDepartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 院系控制器
 */
@Controller
@RequestMapping("/depart")
public class DepartController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(DepartController.class);

    @Autowired
    private IDepartService departService;

    /**
     * 进入院系列表页面
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        logger.debug("into depart/list");
        model.addAttribute("departList",departService.getDepartList());
        return "depart/list";
    }


    /**
     * 添加院系
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(DepartDto departDto) {
        logger.debug("into depart/add departDto:{}", JsonUtils.toJSONString(departDto));
        departService.add(departDto);
        return new JSONResult();
    }


    /**
     * 修改院系
     *
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(DepartDto departDto) {
        logger.debug("into depart/update departDto:{}", JsonUtils.toJSONString(departDto));
        departService.update(departDto);
        return new JSONResult();
    }

    /**
     * 获取院系详情
     *
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(String departId) {
        logger.debug("into depart/detail?readerTypeId={}", departId);
        return new JSONResult(departService.selectById(departId));
    }

    /**
     * 删除院系
     *
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Object del(String departId) {
        logger.debug("into depart/del departId:{}", departId);
        departService.del(departId);
        return new JSONResult();
    }

}
