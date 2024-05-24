package com.datn.hoang.services.dto;

public class PagingUserIdDTO {
    private Long userId;

    private Integer page;

    private Integer size;

    public PagingUserIdDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
