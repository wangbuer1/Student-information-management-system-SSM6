package com.martian.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.martian.enums.YNEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类基类
 */
public class BaseEntity implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 6367488519082660935L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 创建者 - user_id
     */
    @JSONField(serialize = false)
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新人 - user_id
     */
    @JSONField(serialize = false)
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @JSONField(serialize = false)
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 是否有效，Y表示有效，N表示无效
     */
    @JSONField(serialize = false)
    @TableField(value = "is_valid")
    private YNEnum isValid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public YNEnum getIsValid() {
        return isValid;
    }

    public void setIsValid(YNEnum isValid) {
        this.isValid = isValid;
    }
}