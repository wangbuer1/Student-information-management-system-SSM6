package com.martian.vo.tree;

import java.io.Serializable;

/**
 * 基础树
 */
public class BaseTree implements Serializable {

    private static final long serialVersionUID = 2146841022341880273L;
    /**
     * 节点Id
     */
    private String id;
    /**
     * 节点父Id
     */
    private String pId;
    /**
     * 节点名称
     */
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
