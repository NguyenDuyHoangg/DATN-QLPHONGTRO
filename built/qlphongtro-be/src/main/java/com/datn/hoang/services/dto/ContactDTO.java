package com.datn.hoang.services.dto;

import java.time.Instant;
import java.util.Date;

public class ContactDTO {
    private Long contactId;

    private Instant contactCreatedDate;

    private String contactContent;

    private Long contactArticleId;

    private Long contactUserId;

    public ContactDTO() {
    }

    public ContactDTO(Long contactId, Instant contactCreatedDate, String contactContent, Long contactArticleId, Long contactUserId) {
        this.contactId = contactId;
        this.contactCreatedDate = contactCreatedDate;
        this.contactContent = contactContent;
        this.contactArticleId = contactArticleId;
        this.contactUserId = contactUserId;
    }

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
