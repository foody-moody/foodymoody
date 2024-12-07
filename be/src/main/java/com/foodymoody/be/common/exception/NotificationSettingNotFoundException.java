package com.foodymoody.be.common.exception;

public class NotificationSettingNotFoundException extends BusinessException {

    public NotificationSettingNotFoundException() {
        super(ErrorMessage.NOTIFICATION_SETTING_NOT_FOUND);
    }

}
