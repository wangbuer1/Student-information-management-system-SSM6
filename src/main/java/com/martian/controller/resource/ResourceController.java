package com.martian.controller.resource;

import com.martian.common.result.JSONResult;
import com.martian.common.util.JsonUtils;
import com.martian.controller.base.BaseController;
import com.martian.entity.resource.Resource;
import com.martian.enums.resource.ResourceTypeEnum;
import com.martian.service.resource.IResourceService;
import com.martian.vo.tree.ResourceTreeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 资源控制器
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private IResourceService resourceService;

    /**
     * 进入资源列表
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        logger.debug("into resource/list");
        //资源类型
        model.addAttribute("resourceTypeEnum", ResourceTypeEnum.values());
        //资源列表
        model.addAttribute("resourceList",resourceService.getResourceList());
        return "resource/list";
    }

    /**
     * 添加资源
     *
     * @param resource
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(Resource resource) {
        logger.debug("into resource/add resource:{}", JsonUtils.toJSONString(resource));
        resourceService.add(resource);
        return new JSONResult();
    }

    /**
     * 获取资源树
     *
     * @return
     */
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    public Object tree() {
        logger.debug("into resource/tree");
        List<ResourceTreeVo> treeVoList = resourceService.getResourceTree();
        return new JSONResult(treeVoList);
    }
}
