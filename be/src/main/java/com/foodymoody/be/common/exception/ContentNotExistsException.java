package com.foodymoody.be.common.exception;

public class ContentNotExistsException extends BusinessException {

    private static final ErrorMessage errorMessage = ErrorMessage.CONTENT_NOT_EXISTS;

    public ContentNotExistsException() {
        super(errorMessage.getMessage());
    }

    @Override
    public String getCode() {
        return errorMessage.getCode();
    }
}
