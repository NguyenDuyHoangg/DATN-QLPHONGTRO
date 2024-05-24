package com.datn.hoang.services.dto;

import jakarta.persistence.Column;
import lombok.*;


public class ImageDTO {

    private Long imageId;

    private String imageUrl;

    private String imageName;

    private Long imageArticleId;

    public ImageDTO() {
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Long getImageArticleId() {
        return imageArticleId;
    }

    public void setImageArticleId(Long imageArticleId) {
        this.imageArticleId = imageArticleId;
    }
}
