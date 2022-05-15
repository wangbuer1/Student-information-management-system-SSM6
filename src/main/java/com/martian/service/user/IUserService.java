package com.martian.service.user;

import com.baomidou.mybatisplus.service.IService;
import com.martian.dto.user.LoginDto;
import com.martian.dto.user.UpdatePwdDto;
import com.martian.entity.user.User;
import com.martian.enums.role.RoleEnum;
import com.martian.vo.user.LoginVo;

/**
 * 用户表 服务类
 */
public interface IUserService extends IService<User> {


    /**
     * 用户登录
     *
     * @param loginDto
     * @return
     */
    LoginVo login(LoginDto loginDto);

    /**
     * 根据用户名查询用户信息
     *
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);

    /**
     * 删除用户信息
     *
     * @param userId
     */
    void del(String userId);

    /**
     * 修改密码
     *
     * @param userId
     * @param updatePwdDto
     */
    void updatePwd(String userId, UpdatePwdDto updatePwdDto);

    /**
     * 添加用户
     *
     * @param userName 用户名
     * @param password 密码
     * @param role     角色
     * @param objId    teacherId 或 studentId
     */
    User add(String userName, String password, RoleEnum role, String objId);

    /**
     * 修改密码
     */
    void updatePwd(RoleEnum role, String objId, String password);

    /**
     * 删除
     *
     * @param role
     * @param objId
     */
    void del(RoleEnum role, String objId);
}
