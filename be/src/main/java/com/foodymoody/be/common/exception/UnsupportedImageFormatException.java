package com.foodymoody.be.common.exception;

public class UnsupportedImageFormatException extends BusinessException {


    public UnsupportedImageFormatException() {
        super(ErrorMessage.UNSUPPORTED_IMAGE_FORMAT_EXCEPTION);
    }
}
