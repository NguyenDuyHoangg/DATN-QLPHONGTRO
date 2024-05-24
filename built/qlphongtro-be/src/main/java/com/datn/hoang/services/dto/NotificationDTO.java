package com.datn.hoang.services.dto;

import jakarta.persistence.Column;
import lombok.*;
public class NotificationDTO {
    private Long notificationId;

    private String notificationMessage;

    private Long notificationUserId;

    private Long notificationArticleId;

    public NotificationDTO() {
    }


    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public Long getNotificationUserId() {
        return notificationUserId;
    }

    public void setNotificationUserId(Long notificationUserId) {
        this.notificationUserId = notificationUserId;
    }

    public Long getNotificationArticleId() {
        return notificationArticleId;
    }

    public void setNotificationArticleId(Long notificationArticleId) {
        this.notificationArticleId = notificationArticleId;
    }
}
