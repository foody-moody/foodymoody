package com.foodymoody.be.common.exception;

public class ImageUploadFailedException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public ImageUploadFailedException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return errorMessage.getCode();
    }

}
