package com.martian.service.resource.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.martian.common.exception.BusinessAsserts;
import com.martian.common.exception.Exceptions;
import com.martian.common.util.EntityUtils;
import com.martian.common.util.JsonUtils;
import com.martian.common.util.CommonUtils;
import com.martian.common.validate.AddGroup;
import com.martian.common.validate.UpdateGroup;
import com.martian.common.validate.ValidateUtil;
import com.martian.dao.resource.ResourceMapper;
import com.martian.entity.resource.Resource;
import com.martian.enums.YNEnum;
import com.martian.enums.resource.ResourceStateEnum;
import com.martian.enums.resource.ResourceTypeEnum;
import com.martian.service.resource.IResourceService;
import com.martian.vo.tree.ResourceTreeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源表 服务实现类
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {
    /**
     * 日志相关
     */
    private static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
    /**
     * 根节点Id
     */
    private String rootId = "0";

    /**
     * 保存资源
     *
     * @param resource
     */
    @Override
    @Transactional
    public void add(Resource resource) {
        //校验
        ValidateUtil.validateEntity(resource, AddGroup.class);
        if (CommonUtils.isEmpty(resource.getPid())) {
            resource.setPid(rootId);
        }

        //生成树编码【2位为一级】
        String treeCode = generateTreeCode(resource.getPid());
        resource.setTreeCode(treeCode);
        resource.setIsLeaf(YNEnum.YES);
        EntityUtils.init(resource);
        boolean flag = super.insert(resource);
        if (!flag) {
            logger.error("添加资源信息错误：{}", JsonUtils.toJSONString(resource));
        }
        BusinessAsserts.isTrue(flag, Exceptions.System.ADD_FAIL);

        //父节点
        if(!rootId.equals(resource.getPid())){
            Resource param = new Resource();
            param.setId(resource.getPid());
            param.setIsLeaf(YNEnum.NO);
            EntityUtils.init(param);
            flag = super.updateById(param);
            if (!flag) {
                logger.error("更新父资源信息错误：{}", JsonUtils.toJSONString(param));
            }
            BusinessAsserts.isTrue(flag, Exceptions.System.ADD_FAIL);
        }
    }

    /**
     * 生成树编码【2位为一级】
     *
     * @param pid
     * @return
     */
    private String generateTreeCode(String pid) {
        //子节点
        Resource param = new Resource();
        param.setPid(pid);
        List<Resource> childList = super.selectList(new EntityWrapper<Resource>(param).orderBy("tree_code", false));

        //树编码
        String treeCode;
        if (CommonUtils.isNotEmpty(childList)) {
            Resource child = childList.get(0);
            treeCode = String.valueOf(Integer.parseInt(child.getTreeCode()) + 1);
        } else {
            if (rootId.equals(pid)) {
                treeCode = "10";
            } else {
                //父节点
                Resource parent = super.selectById(pid);
                treeCode = parent.getTreeCode() + "01";
            }
        }
        return treeCode;
    }

    /**
     * 修改资源
     *
     * @param resource
     */
    @Override
    public void update(Resource resource) {
        //校验
        ValidateUtil.validateEntity(resource, UpdateGroup.class);

        EntityUtils.init(resource);
        boolean flag = super.updateById(resource);
        if (!flag) {
            logger.error("修改资源信息错误：{}", JsonUtils.toJSONString(resource));
        }
        BusinessAsserts.isTrue(flag, Exceptions.System.UPDATE_FAIL);
    }

    /**
     * 获取资源树
     *
     * @return
     */
    @Override
    public List<ResourceTreeVo> getResourceTree() {
        return this.baseMapper.getResourceTree();
    }

    /**
     * 获取资源列表
     *
     * @return
     */
    @Override
    public List<Resource> getResourceList() {
        Resource param = new Resource();
        param.setIsValid(YNEnum.YES);
        param.setState(ResourceStateEnum.NORMAL);

        List<Resource> resourceList = super.selectList(new EntityWrapper<Resource>(param).orderBy("tree_code"));
        if (CommonUtils.isEmpty(resourceList)) {
            resourceList = new ArrayList<>();
        }
        return resourceList;
    }

    /**
     * 查询用户的所有权限
     *
     * @param userId
     * @param resourceType
     * @return
     */
    @Override
    public List<Resource> getPermissionList(String userId,ResourceTypeEnum resourceType) {
        if(CommonUtils.isEmpty(userId)){
            return null;
        }
        List<Resource> permissionList = this.baseMapper.getPermissionList(userId,resourceType);
        return permissionList;
    }
}
