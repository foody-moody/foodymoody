package com.foodymoody.be.common.exception;

public class InvalidCommentIdException extends BusinessException {

    public InvalidCommentIdException() {
        super(ErrorMessage.INVALID_COMMENT_ID);
    }
}
