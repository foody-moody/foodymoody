package com.foodymoody.be.common.exception;

public class ContentIsSpaceException extends BusinessException {

    private static final ErrorMessage errorMessage = ErrorMessage.CONTENT_IS_SPACE;

    public ContentIsSpaceException() {
        super(errorMessage.getMessage());
    }

    @Override
    public String getCode() {
        return errorMessage.getCode();
    }
}
