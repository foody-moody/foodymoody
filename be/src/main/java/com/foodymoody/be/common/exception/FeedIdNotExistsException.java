package com.foodymoody.be.common.exception;

public class FeedIdNotExistsException extends BusinessException {

    private static final ErrorMessage errorMessage = ErrorMessage.FEED_ID_NOT_EXISTS;

    public FeedIdNotExistsException() {
        super(errorMessage.getMessage());
    }

    @Override
    public String getCode() {
        return errorMessage.getCode();
    }
}
