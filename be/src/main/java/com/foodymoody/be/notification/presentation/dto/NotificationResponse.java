package com.foodymoody.be.notification.presentation.dto;

import com.foodymoody.be.notification.domain.NotificationId;
import java.time.LocalDateTime;

public class NotificationResponse {

    private String id;
    private String fromId;
    private String formNickname;
    private String fromProfileImageUrl;
    private String link;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public NotificationResponse(NotificationId id, String fromId, String formNickname, String fromProfileImageUrl,
            String link, String message, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id.getValue();
        this.fromId = fromId;
        this.formNickname = formNickname;
        this.fromProfileImageUrl = fromProfileImageUrl;
        this.link = link;
        this.message = message;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getFromId() {
        return fromId;
    }

    public String getFormNickname() {
        return formNickname;
    }

    public String getFromProfileImageUrl() {
        return fromProfileImageUrl;
    }

    public String getLink() {
        return link;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
