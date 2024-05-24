package com.datn.hoang.services.dto;

import com.datn.hoang.common.SqlEscaperUtil;

public class SearchUserDTO {
    private String searchString;

    private Integer page;

    private Integer size;

    public SearchUserDTO() {
    }

    public SearchUserDTO(String searchString) {
        this.searchString = searchString;
    }

    public void handleBeforeSearch() {
        if (this.searchString != null) searchString = SqlEscaperUtil.escape(searchString.trim());
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

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}
