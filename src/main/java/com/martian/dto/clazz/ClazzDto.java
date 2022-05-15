package com.martian.dto.clazz;

import java.io.Serializable;

/**
 * 班级DTO
 */
public class ClazzDto implements Serializable {
    private static final long serialVersionUID = 5046620020957687340L;
    /**
     * Id
     */
    private String id;
    /**
     * 班级名称
     */
    private String name;
    /**
     * 院系ID
     */
    private String departId;
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

    public String getClazzDesc() {
        return clazzDesc;
    }

    public void setClazzDesc(String clazzDesc) {
        this.clazzDesc = clazzDesc;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }
}
