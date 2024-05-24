package com.datn.hoang.services.dto;

import com.datn.hoang.common.SqlEscaperUtil;

public class SearchDTO {
    private Long roomTypeId;
    private String district;
    private String wards;
    private Long minPrice;
    private Long maxPrice;
    private Long minArea;
    private Long maxArea;
    private String searchString;

    private Integer page;

    private Integer size;

    public void handleBeforeSearch() {
        if (this.searchString != null) searchString = SqlEscaperUtil.escape(searchString.trim());
    }

    public SearchDTO() {
    }

    public SearchDTO(Long roomTypeId, String district, String wards, Long minPrice, Long maxPrice, Long minArea, Long maxArea, String searchString, Integer page, Integer size) {
        this.roomTypeId = roomTypeId;
        this.district = district;
        this.wards = wards;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minArea = minArea;
        this.maxArea = maxArea;
        this.searchString = searchString;
        this.page = page;
        this.size = size;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWards() {
        return wards;
    }

    public void setWards(String wards) {
        this.wards = wards;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Long getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Long maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Long getMinArea() {
        return minArea;
    }

    public void setMinArea(Long minArea) {
        this.minArea = minArea;
    }

    public Long getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(Long maxArea) {
        this.maxArea = maxArea;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
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
