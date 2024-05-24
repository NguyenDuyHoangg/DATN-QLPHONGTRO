package com.datn.hoang.services.dto;

public class SearchContactDTO {
    private Long search;

    private Integer page;

    private Integer size;

    public SearchContactDTO() {
    }

    public Long getSearch() {
        return search;
    }

    public void setSearch(Long search) {
        this.search = search;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
