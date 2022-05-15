package com.martian.common.enums;

import java.io.Serializable;

/**
 * 枚举对象
 */
public class EnumObj implements Serializable {

    private static final long serialVersionUID = 1795825437349987L;

    public String code;
    public String label;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public EnumObj() {
    }

    public EnumObj(String code, String label) {
        this.code = code;
        this.label = label;
    }
}
