package com.datn.hoang.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.Instant;
import java.util.Date;
@Entity
@Table(name = "tblcontact")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactId;

    @Column(name = "contact_created_date")
    private Instant contactCreatedDate;

    @Column(name = "contact_content")
    private String contactContent;

    @Column(name = "contact_article_id")
    private Long contactArticleId;

    @Column(name = "contact_user_id")
    private Long contactUserId;

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Instant getContactCreatedDate() {
        return contactCreatedDate;
    }

    public void setContactCreatedDate(Instant contactCreatedDate) {
        this.contactCreatedDate = contactCreatedDate;
    }

    public String getContactContent() {
        return contactContent;
    }

    public void setContactContent(String contactContent) {
        this.contactContent = contactContent;
    }

    public Long getContactArticleId() {
        return contactArticleId;
    }

    public void setContactArticleId(Long contactArticleId) {
        this.contactArticleId = contactArticleId;
    }

    public Long getContactUserId() {
        return contactUserId;
    }

    public void setContactUserId(Long contactUserId) {
        this.contactUserId = contactUserId;
    }
}
