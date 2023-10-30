package com.foodymoody.be.common.exception;

public class CommentDeletedException extends BusinessException {

    private static final ErrorMessage errorMessage = ErrorMessage.COMMENT_DELETED;

    public CommentDeletedException() {
        super(errorMessage.getMessage());
    }

    @Override
    String getCode() {
        return errorMessage.getCode();
    }
}
