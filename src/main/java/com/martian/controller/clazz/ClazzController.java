package com.martian.controller.clazz;

import com.martian.common.page.PageVo;
import com.martian.common.result.JSONResult;
import com.martian.common.util.JsonUtils;
import com.martian.controller.base.BaseController;
import com.martian.dto.clazz.ClazzDto;
import com.martian.dto.clazz.ClazzSearchDto;
import com.martian.entity.clazz.Clazz;
import com.martian.service.clazz.IClazzService;
import com.martian.service.depart.IDepartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 班级控制器
 */
@Controller
@RequestMapping("/clazz")
public class ClazzController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClazzController.class);

    @Autowired
    private IClazzService clazzService;

    @Autowired
    private IDepartService departService;

    /**
     * 进入班级列表页面
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        LOGGER.debug("into clazz/list");
        model.addAttribute("departList", departService.getDepartList());
        return "clazz/list";
    }

    /**
     * 搜索班级
     *
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Object search(ClazzSearchDto clazzSearchDto) {
        LOGGER.debug("into clazz/search clazzSearchDto:{}", JsonUtils.toJSONString(clazzSearchDto));
        PageVo pageVo = clazzService.searchClazzVoPage(clazzSearchDto);
        return new JSONResult(pageVo);
    }

    /**
     * 根据院系Id搜索班级
     *
     * @return
     */
    @RequestMapping(value = "/search/{departId}", method = RequestMethod.GET)
    @ResponseBody
    public Object searchByDepartId(@PathVariable String departId) {
        LOGGER.debug("into clazz/searchByDepartId/{}", departId);
        List<Clazz> clazzList = clazzService.searchByDepartId(departId);
        return new JSONResult(clazzList);
    }

    /**
     * 添加班级
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ClazzDto clazzDto) {
        LOGGER.debug("into clazz/add clazzDto:{}", JsonUtils.toJSONString(clazzDto));
        clazzService.add(clazzDto);
        return new JSONResult();
    }


    /**
     * 修改班级
     *
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(ClazzDto clazzDto) {
        LOGGER.debug("into clazz/update clazzDto:{}", JsonUtils.toJSONString(clazzDto));
        clazzService.update(clazzDto);
        return new JSONResult();
    }

    /**
     * 获取班级详情
     *
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(String clazzId) {
        LOGGER.debug("into clazz/detail?clazzId={}", clazzId);
        return new JSONResult(clazzService.selectById(clazzId));
    }

    /**
     * 删除班级
     *
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Object del(String clazzId) {
        LOGGER.debug("into clazz/del clazzId:{}", clazzId);
        clazzService.del(clazzId);
        return new JSONResult();
    }
}
