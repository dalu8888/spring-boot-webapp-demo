package com.digitalchina.common.pagination;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 分页实现类
 */
public class GenericPagination implements Pagination {

    private static final long INITIAL_TOTAL_SIZE = Long.MIN_VALUE;

    private long totalSize = INITIAL_TOTAL_SIZE;// 总记录数目

    private long pageSize = PAGE_SIZE;// 每页记录数

    private long currentPage = 1;// 查询的页号(查询第currentPage页)

    public GenericPagination(long totalSize, long pageSize, long currentPage) {
        this.totalSize = totalSize;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    /**
     * 获得起始索引
     *
     * @return 起始索引
     */
    @Override
    public long getStartIndex() {
        long index = 0;
        if (getTotalSize() == 0) {
            return index;
        }
        index = (getCurrentPage() - 1) * getPageSize();
        if (index < 0) {
            index = 0;
        }
        return index;
    }

    /**
     * 获得结束索引
     *
     * @return 结束索引
     */
    @Override
    public long getEndIndex() {
        long endIndex = getPageSize();
        if (endIndex > getTotalSize()) {
            endIndex = getTotalSize();
        }
        return endIndex;
    }

    /**
     * 取得每页记录数。
     *
     * @return 每页显示的记录数。
     */
    @Override
    public long getPageSize() {
        return pageSize;
    }

    /**
     * 获取总记录数
     *
     * @return 总记录数
     */
    @Override
    public long getTotalSize() {
        if (this.totalSize == INITIAL_TOTAL_SIZE) {
            throw new IllegalStateException("尚未设置总记录数，无法进行分页计算");
        }
        return totalSize;
    }

    /**
     * 取得当前页号
     *
     * @return 当前页号
     */
    @Override
    public long getCurrentPage() {
        if (currentPage <= 0) currentPage = 1;
        return currentPage;
    }

    /**
     * 返回总页面数目。
     *
     * @return 总分页数目。
     */
    @Override
    public long getTotalPage() {
        return (getTotalSize() + getPageSize() - 1) / getPageSize();
    }

}
