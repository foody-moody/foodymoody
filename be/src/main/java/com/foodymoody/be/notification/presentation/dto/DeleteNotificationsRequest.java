package com.foodymoody.be.notification.presentation.dto;

import java.util.List;

public class DeleteNotificationsRequest {

    private List<String> notificationIds;

    public List<String> getNotificationIds() {
        return notificationIds;
    }

    public void setNotificationIds(List<String> notificationIds) {
        this.notificationIds = notificationIds;
    }
}
