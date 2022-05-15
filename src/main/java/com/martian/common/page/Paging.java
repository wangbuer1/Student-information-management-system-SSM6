package com.martian.common.page;

import java.util.List;

/**
 * 自定义分页标签 - 分页接口
 */
public interface Paging<T> {

    Integer getPageNo();

    Integer getPageSize();

    Integer getTotalPage();

    Integer getTotalCount();

    List<T> getList();

    Integer getLastPage();

    boolean isHasPreviousPage();

    boolean isHasNextPage();

    int getPreviousPage();

    int getNextPage();
}
