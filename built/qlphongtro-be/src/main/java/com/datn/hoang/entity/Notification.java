package com.datn.hoang.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
@Entity
@Table(name = "tblnotification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @Column(name = "notification_message")
    private String notificationMessage;

    @Column(name = "notification_user_id")
    private Long notificationUserId;

    @Column(name = "notification_article_id")
    private Long notificationArticleId;

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
