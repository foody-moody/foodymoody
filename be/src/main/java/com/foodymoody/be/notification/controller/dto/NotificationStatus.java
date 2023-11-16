package com.foodymoody.be.notification.controller.dto;

public class NotificationStatus {

    private boolean isRead;

    public NotificationStatus() {
    }

    public NotificationStatus(boolean isRead) {
        this.isRead = isRead;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
