package com.martian.common.page;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * 分页VO - 用于返回到页面的对象
 */
public class PageVo implements Paging {

    /**
     * 当前页面
     */
    private Integer pageNo;

    /**
     * 分页大小
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer totalPage;


    /**
     * 总的记录数
     */
    private Integer totalCount;

    /**
     * 分页列表
     */
    private List list;

    public PageVo(Page page) {
        this.pageNo = page.getCurrent();
        this.pageSize = page.getSize();
        this.totalPage = page.getPages();
        this.totalCount = page.getTotal();
        this.list = page.getRecords();
    }

    @Override
    public Integer getPageNo() {
        return pageNo;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    @Override
    public Integer getTotalPage() {
        return totalPage;
    }

    @Override
    public Integer getTotalCount() {
        return totalCount;
    }

    @Override
    public List getList() {
        return list;
    }

    @Override
    public Integer getLastPage() {
        return this.totalPage;
    }

    @Override
    public boolean isHasPreviousPage() {
        return pageNo > 1;
    }

    @Override
    public boolean isHasNextPage() {
        return this.pageNo < this.totalPage;
    }

    @Override
    public int getPreviousPage() {
        return pageNo - 1;
    }

    @Override
    public int getNextPage() {
        return this.pageNo + 1;
    }
}
