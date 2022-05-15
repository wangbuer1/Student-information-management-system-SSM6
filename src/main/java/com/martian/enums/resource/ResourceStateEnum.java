package com.martian.enums.resource;

import com.martian.common.enums.IEnum;

/**
 * 资源状态
 */
public enum ResourceStateEnum implements IEnum {

    NORMAL("1", "正常"),
    DISABLE("0", "禁用");

    public String code;
    public String label;

    ResourceStateEnum(String code, String label) {
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
