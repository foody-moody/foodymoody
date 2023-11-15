package com.foodymoody.be.notification.controller.dto;

import java.util.List;

public class ChangeAllNotificationStatusRequest {

    private List<String> notificationIds;
    private boolean isRead;

    public ChangeAllNotificationStatusRequest() {
    }

    public List<String> getNotificationIds() {
        return notificationIds;
    }

    public void setNotificationIds(List<String> notificationIds) {
        this.notificationIds = notificationIds;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
