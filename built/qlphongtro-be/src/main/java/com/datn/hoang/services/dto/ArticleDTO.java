package com.datn.hoang.services.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

public class ArticleDTO {
    private Long articleId;

    private String articleTitle;

    private String articleSummary;

    private String articleContent;

    private Long articleApproved;

    private Date articleCreatedDate;

    private Long articleStatus;

    private Date articleLastModified;

    private Long articleUserId;

    private Long articleRoomId;

    private Long articleBlocked;

    private Long articleNotApproved;

    private Date articleExpirationDate;

    public ArticleDTO() {
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Long getArticleApproved() {
        return articleApproved;
    }

    public void setArticleApproved(Long articleApproved) {
        this.articleApproved = articleApproved;
    }

    public Date getArticleCreatedDate() {
        return articleCreatedDate;
    }

    public void setArticleCreatedDate(Date articleCreatedDate) {
        this.articleCreatedDate = articleCreatedDate;
    }

    public Long getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Long articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Date getArticleLastModified() {
        return articleLastModified;
    }

    public void setArticleLastModified(Date articleLastModified) {
        this.articleLastModified = articleLastModified;
    }

    public Long getArticleUserId() {
        return articleUserId;
    }

    public void setArticleUserId(Long articleUserId) {
        this.articleUserId = articleUserId;
    }

    public Long getArticleRoomId() {
        return articleRoomId;
    }

    public void setArticleRoomId(Long articleRoomId) {
        this.articleRoomId = articleRoomId;
    }

    public Long getArticleBlocked() {
        return articleBlocked;
    }

    public void setArticleBlocked(Long articleBlocked) {
        this.articleBlocked = articleBlocked;
    }

    public Long getArticleNotApproved() {
        return articleNotApproved;
    }

    public void setArticleNotApproved(Long articleNotApproved) {
        this.articleNotApproved = articleNotApproved;
    }

    public Date getArticleExpirationDate() {
        return articleExpirationDate;
    }

    public void setArticleExpirationDate(Date articleExpirationDate) {
        this.articleExpirationDate = articleExpirationDate;
    }
}
