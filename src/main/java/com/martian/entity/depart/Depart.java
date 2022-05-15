package com.martian.entity.depart;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.martian.entity.BaseEntity;

/**
 * 院系表
 */
@TableName("t_depart")
public class Depart extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 院系名称
     */
    private String name;
    /**
     * 院系描述
     */
    @TableField("depart_desc")
    private String departDesc;


    public String getName() {
        return name;
    }

    public Depart setName(String name) {
        this.name = name;
        return this;
    }

    public String getDepartDesc() {
        return departDesc;
    }

    public Depart setDepartDesc(String departDesc) {
        this.departDesc = departDesc;
        return this;
    }

    @Override
    public String toString() {
        return "Depart{" +
        ", name=" + name +
        ", departDesc=" + departDesc +
        "}";
    }
}
