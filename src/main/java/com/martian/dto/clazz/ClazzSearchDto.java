package com.martian.dto.clazz;

import com.martian.common.page.BasePageDto;

/**
 * 班级搜索DTO
 */
public class ClazzSearchDto extends BasePageDto {
    private static final long serialVersionUID = -7406178024782691090L;

    /**
     * 班级名称
     */
    private String clazzName;
    /**
     * 院系Id
     */
    private String departId;

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }
}
