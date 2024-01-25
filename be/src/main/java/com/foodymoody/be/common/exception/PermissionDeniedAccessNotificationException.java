package com.foodymoody.be.common.exception;

public class PermissionDeniedAccessNotificationException extends BusinessException {

    public PermissionDeniedAccessNotificationException() {
        super(ErrorMessage.PERMISSION_DENIED_ACCESS_NOTIFICATION);
    }
}
