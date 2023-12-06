package com.foodymoody.be.common.exception;

public class InvalidImageFileException extends IllegalArgumentException {

    public InvalidImageFileException() {
        super(ErrorMessage.INVALID_IMAGE_FILE.getMessage());
    }

    public String getCode() {
        return ErrorMessage.INVALID_IMAGE_FILE.getCode();
    }
}
