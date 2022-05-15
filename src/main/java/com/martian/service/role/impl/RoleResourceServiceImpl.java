package com.martian.service.role.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.martian.dao.role.RoleResourceMapper;
import com.martian.entity.role.RoleResource;
import com.martian.service.role.IRoleResourceService;
import org.springframework.stereotype.Service;

/**
 * 角色-资源表 服务实现类
 */
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements IRoleResourceService {
	
}
