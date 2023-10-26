package com.foodymoody.be.common.exception;

public class RegisterCommentRequestNotNullException extends BusinessException {

    private static final ErrorMessage errorMessage = ErrorMessage.REGISTER_COMMENT_REQUEST_NOT_NULL;

    public RegisterCommentRequestNotNullException() {
        super(errorMessage.getMessage());
    }

    @Override
    public String getCode() {
        return errorMessage.getCode();
    }
}
