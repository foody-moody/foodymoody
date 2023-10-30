package com.foodymoody.be.common.exception;

public class CommentNotExistsException extends BusinessException {

    private static final ErrorMessage errorMessage = ErrorMessage.COMMENT_NOT_EXISTS;

    public CommentNotExistsException() {
        super(errorMessage.getMessage());
    }

    @Override
    String getCode() {
        return errorMessage.getCode();
    }
}
