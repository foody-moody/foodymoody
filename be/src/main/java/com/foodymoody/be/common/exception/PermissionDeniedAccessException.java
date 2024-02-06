package com.foodymoody.be.common.exception;

public class PermissionDeniedAccessException extends BusinessException {

    public PermissionDeniedAccessException() {
        super(ErrorMessage.PERMISSION_DENIED_ACCESS);
    }
}
