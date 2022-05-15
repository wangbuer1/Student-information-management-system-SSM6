package com.martian.enums.resource;

import com.martian.common.enums.IEnum;

/**
 * 资源类别：1菜单2按钮
 */
public enum ResourceTypeEnum implements IEnum {

    MENU("1", "菜单"),
    BUTTON("2", "按钮");

    public String code;
    public String label;

    ResourceTypeEnum(String code, String label) {
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
