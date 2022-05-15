package com.martian.dao.resource;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.martian.entity.resource.Resource;
import com.martian.enums.resource.ResourceTypeEnum;
import com.martian.vo.tree.ResourceTreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资源表 Mapper 接口
 */
public interface ResourceMapper extends BaseMapper<Resource> {
    /**
     * 获取资源树
     *
     * @return
     */
    List<ResourceTreeVo> getResourceTree();


    /**
     * 查询用户的权限
     * @param userId
     * @param resourceType
     * @return
     */
    List<Resource> getPermissionList(@Param("userId") String userId, @Param("resourceType") ResourceTypeEnum resourceType);
}