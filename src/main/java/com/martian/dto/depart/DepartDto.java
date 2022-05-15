package com.martian.dto.depart;

import java.io.Serializable;

/**
 * 院系DTO
 */
public class DepartDto implements Serializable {
    private static final long serialVersionUID = 5046620020957687340L;
    /**
     * Id
     */
    private String id;
    /**
     * 院系名称
     */
    private String name;
    /**
     * 院系描述
     */
    private String departDesc;

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

    public String getDepartDesc() {
        return departDesc;
    }

    public void setDepartDesc(String departDesc) {
        this.departDesc = departDesc;
    }
}
