package com.martian.entity.clazz;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.martian.entity.BaseEntity;

/**
 * 班级表
 */
@TableName("t_clazz")
public class Clazz extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 班级名称
     */
    private String name;

    /**
     * 院系ID
     */
    @TableField("depart_id")
    private String departId;
    /**
     * 班级描述
     */
    @TableField("clazz_desc")
    private String clazzDesc;


    public String getName() {
        return name;
    }

    public Clazz setName(String name) {
        this.name = name;
        return this;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getClazzDesc() {
        return clazzDesc;
    }

    public Clazz setClazzDesc(String clazzDesc) {
        this.clazzDesc = clazzDesc;
        return this;
    }
}
