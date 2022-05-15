package com.martian.dto.user;

/**
 * 修改密码DTO
 */
public class UpdatePwdDto {
    /**
     * 新密码
     */
    private String newPwd;

    /**
     * 重复新密码
     */
    private String reNewPwd;

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getReNewPwd() {
        return reNewPwd;
    }

    public void setReNewPwd(String reNewPwd) {
        this.reNewPwd = reNewPwd;
    }
}
