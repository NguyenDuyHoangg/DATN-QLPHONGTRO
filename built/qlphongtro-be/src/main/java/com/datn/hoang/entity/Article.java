package com.datn.hoang.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Date;
@Entity
@Table(name = "tblarticle")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Article {
    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Column(name = "article_title")
    private String articleTitle;

    @Column(name = "article_summary")
    private String articleSummary;

    @Column(name = "article_content")
    private String articleContent;

    @Column(name = "article_approved")
    private Long articleApproved;

    @Column(name = "article_created_date")
    private Date articleCreatedDate;

    @Column(name = "article_status")
    private Long articleStatus;

    @Column(name = "article_last_modified")
    private Date articleLastModified;

    @Column(name = "article_user_id")
    private Long articleUserId;

    @Column(name = "article_room_id")
    private Long articleRoomId;

    @Column(name = "article_blocked")
    private Long articleBlocked;

    @Column(name = "article_not_approved")
    private Long articleNotApproved;

    @Column(name = "article_expiration_date")
    private Date articleExpirationDate;

    public Date getArticleExpirationDate() {
        return articleExpirationDate;
    }

    public void setArticleExpirationDate(Date articleExpirationDate) {
        this.articleExpirationDate = articleExpirationDate;
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
}
