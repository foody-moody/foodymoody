package com.foodymoody.be.common.exception;

public class ContentIsOver200Exception extends BusinessException {

    private static final ErrorMessage errorMessage = ErrorMessage.CONTENT_IS_OVER_200;

    public ContentIsOver200Exception() {
        super(errorMessage.getMessage());
    }

    @Override
    public String getCode() {
        return errorMessage.getCode();
    }
}
