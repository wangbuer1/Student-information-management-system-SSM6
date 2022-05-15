package com.martian.enums.course;

import com.martian.common.enums.IEnum;

/**
 * 学期的枚举
 */
public enum TermEnum implements IEnum {

    UP_TERM("0", "上学期"),
    LOWER_TERM("1", "下学期");

    public String code;
    public String label;

    TermEnum(String code, String label) {
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
