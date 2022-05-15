package com.martian.vo.user;


import com.martian.entity.resource.Resource;
import com.martian.entity.user.User;

import java.io.Serializable;
import java.util.List;

/**
 * 用户登录信息
 */
public class LoginVo implements Serializable {
    private static final long serialVersionUID = 6214031630565144235L;
    /**
     * 登录用户
     */
    private User user;
    /**
     * 用户菜单权限列表
     */
    private List<Resource> menuList;
    /**
     * 用户所有权限列表
     */
    private List<Resource> permissionList;

    /**
     * 读者借阅证编号，管理员为空
     */
    private String cardNo;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Resource> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Resource> menuList) {
        this.menuList = menuList;
    }

    public List<Resource> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Resource> permissionList) {
        this.permissionList = permissionList;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
