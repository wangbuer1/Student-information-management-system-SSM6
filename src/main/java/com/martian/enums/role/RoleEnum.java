package com.martian.enums.role;

import com.martian.common.enums.IEnum;

/**
 * 角色的枚举,对应t_role表
 */
public enum RoleEnum implements IEnum {

    ADMIN("1", "管理员"),
    TEACHER("2", "教师"),
    STUDENT("3", "学生"),;

    public String code;
    public String label;

    RoleEnum(String code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
