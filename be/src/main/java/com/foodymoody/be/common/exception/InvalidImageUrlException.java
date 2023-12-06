package com.foodymoody.be.common.exception;

public class InvalidImageUrlException extends BusinessException {

    public InvalidImageUrlException() {
        super(ErrorMessage.INVALID_IMAGE_URL);
    }
}
