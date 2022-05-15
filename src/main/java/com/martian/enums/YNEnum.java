package com.martian.enums;

import com.martian.common.enums.IEnum;

/**
 * 是否有效的枚举
 */
public enum YNEnum implements IEnum {

    YES("Y", "是"),
    NO("N", "否");

    public String code;
    public String label;

    YNEnum(String code, String label) {
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
