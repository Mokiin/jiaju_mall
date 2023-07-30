package com.kiin.furns.entity;

import java.util.List;

public class Page<T> {

    public static final Integer PAGE_SIZE = 3;
    // 每页显示多少条数据
    private Integer pageSize = PAGE_SIZE;
    // 第几页
    private Integer pageNo;
    // 显示的数据 DAO层获取
    private List<T> items;
    // 一共有多少条数据 DAO 层获取
    private Integer totalRow;
    //共有多少页
    private Integer pageTotalCount;
    // 分页导航
    private String url;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Integer totalRow) {
        this.totalRow = totalRow;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
