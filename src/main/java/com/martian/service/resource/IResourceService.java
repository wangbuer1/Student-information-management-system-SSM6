package com.martian.service.resource;

import com.baomidou.mybatisplus.service.IService;
import com.martian.entity.resource.Resource;
import com.martian.enums.resource.ResourceTypeEnum;
import com.martian.vo.tree.ResourceTreeVo;

import java.util.List;

/**
 * 资源表 服务类
 */
public interface IResourceService extends IService<Resource> {

    /**
     * 保存资源
     *
     * @param resource
     */
    void add(Resource resource);

    /**
     * 修改资源
     *
     * @param resource
     */
    void update(Resource resource);

    /**
     * 获取资源树
     *
     * @return
     */
    List<ResourceTreeVo> getResourceTree();

    /**
     * 获取资源列表
     *
     * @return
     */
    List<Resource> getResourceList();

    /**
     * 查询用户的权限
     *
     * @param userId
     * @param resourceType
     * @return
     */
    List<Resource> getPermissionList(String userId, ResourceTypeEnum resourceType);
}
