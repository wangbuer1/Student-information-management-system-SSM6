package com.martian.service.user.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.martian.common.constant.Constant;
import com.martian.common.util.EntityUtils;
import com.martian.dao.user.UserRoleMapper;
import com.martian.entity.user.UserRole;
import com.martian.service.user.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户角色表 服务实现类
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    /**
     * 添加用户角色
     *
     * @param userId
     */
    @Override
    public void addUserRole(String userId) {
        UserRole userRole = new UserRole();
        EntityUtils.init(userRole);
        userRole.setUserId(userId);
        userRole.setRoleId(Constant.READER_ROLE_ID);
        super.insert(userRole);
    }
}
