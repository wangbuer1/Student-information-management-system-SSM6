package com.martian.entity.resource;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.martian.common.validate.AddGroup;
import com.martian.common.validate.UpdateGroup;
import com.martian.entity.BaseEntity;
import com.martian.enums.YNEnum;
import com.martian.enums.resource.ResourceStateEnum;
import com.martian.enums.resource.ResourceTypeEnum;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 资源表
 */
@TableName("t_resource")
public class Resource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父资源id
     */
    private String pid;
    /**
     * 名称
     */
    @NotBlank(message = "资源名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Length(message = "资源名称不能超过10个字符", groups = {AddGroup.class, UpdateGroup.class})
    private String name;
    /**
     * 菜单路径
     */
    private String url;
    /**
     * 图标
     */
    private String icon;
    /**
     * 资源类别：1菜单2按钮
     */
    @TableField("resource_type")
    private ResourceTypeEnum resourceType;
    /**
     * 树编码
     */
    @TableField("tree_code")
    private String treeCode;
    /**
     * 状态
     */
    private ResourceStateEnum state;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 是否叶子节点
     */
    private YNEnum isLeaf;


    public String getPid() {
        return pid;
    }

    public Resource setPid(String pid) {
        this.pid = pid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Resource setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Resource setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public Resource setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public Resource setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public ResourceTypeEnum getResourceType() {
        return resourceType;
    }

    public Resource setResourceType(ResourceTypeEnum resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public ResourceStateEnum getState() {
        return state;
    }

    public Resource setState(ResourceStateEnum state) {
        this.state = state;
        return this;
    }

    public String getTreeCode() {
        return treeCode;
    }

    public Resource setTreeCode(String treeCode) {
        this.treeCode = treeCode;
        return this;
    }

    public YNEnum getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(YNEnum isLeaf) {
        this.isLeaf = isLeaf;
    }
}
