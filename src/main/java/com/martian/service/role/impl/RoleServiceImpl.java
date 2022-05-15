package com.martian.service.role.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.martian.dao.role.RoleMapper;
import com.martian.entity.role.Role;
import com.martian.service.role.IRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色表 服务实现类
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
	
}
