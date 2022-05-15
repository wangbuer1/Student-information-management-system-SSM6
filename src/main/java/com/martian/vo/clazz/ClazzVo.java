package com.martian.vo.clazz;

import java.io.Serializable;

/**
 * 班级VO
 */
public class ClazzVo implements Serializable {
    private static final long serialVersionUID = 1031491823435295680L;
    /**
     * Id
     */
    private String id;
    /**
     * 班级名称
     */
    private String name;

    /**
     * 院系名称
     */
    private String departName;
    /**
     * 班级描述
     */
    private String clazzDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getClazzDesc() {
        return clazzDesc;
    }

    public void setClazzDesc(String clazzDesc) {
        this.clazzDesc = clazzDesc;
    }
}
