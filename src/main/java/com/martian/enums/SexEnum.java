package com.martian.enums;

import com.martian.common.enums.IEnum;

/**
 * 性别的枚举
 */
public enum SexEnum implements IEnum {

    MALE("1", "男"),
    FEMALE("0", "女");

    public String code;
    public String label;

    SexEnum(String code, String label) {
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
