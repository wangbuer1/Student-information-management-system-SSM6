package com.martian.service.user;

import com.baomidou.mybatisplus.service.IService;
import com.martian.entity.user.UserRole;

/**
 * 用户角色表 服务类
 */
public interface IUserRoleService extends IService<UserRole> {
    /**
     * 添加用户角色
     * @param userId
     */
    void addUserRole(String userId);
}
