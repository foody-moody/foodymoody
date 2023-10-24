package com.foodymoody.be.common.exception;

public class ContentIsEmptyException extends BusinessException {

    private static final ErrorMessage errorMessage = ErrorMessage.CONTENT_IS_EMPTY;

    public ContentIsEmptyException() {
        super(errorMessage.getMessage());
    }

    @Override
    public String getCode() {
        return errorMessage.getCode();
    }
}
