package com.martian.common.page;

import java.io.Serializable;

/**
 *
 */
public class BasePageDto implements Serializable{
    private static final long serialVersionUID = 5721842126189663391L;
    /**
     * 当前页
     */
    private int pageNo = 1;
    /**
     * 页大小
     */
    private int pageSize = 20;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
