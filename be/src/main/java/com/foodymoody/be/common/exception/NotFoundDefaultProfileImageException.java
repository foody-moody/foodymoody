package com.foodymoody.be.common.exception;

public class NotFoundDefaultProfileImageException extends BusinessException {

    public NotFoundDefaultProfileImageException() {
        super(ErrorMessage.INVALID_IMAGE_ID);
    }

}
