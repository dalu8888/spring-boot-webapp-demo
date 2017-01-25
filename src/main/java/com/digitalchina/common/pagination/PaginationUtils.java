package com.digitalchina.common.pagination;


/**
 * 获取分页检索参数
 */
public class PaginationUtils {

    public static Page getPageParam(long totalSize, long pageSize, long currentPage) {
        Pagination pagination = new GenericPagination(totalSize,pageSize,currentPage);
        Page page = new Page();
        page.setStartIndex(pagination.getStartIndex());
        page.setEndIndex(pagination.getEndIndex());
        page.setTotalPage(pagination.getTotalPage());
        page.setCurrentPage(pagination.getCurrentPage());
        page.setTotalSize(pagination.getTotalSize());
        return page;
    }

}
