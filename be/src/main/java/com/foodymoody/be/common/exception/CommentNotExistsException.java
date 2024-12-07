package com.foodymoody.be.common.exception;

public class CommentNotExistsException extends BusinessException {

    public CommentNotExistsException() {
        super(ErrorMessage.COMMENT_NOT_EXISTS);
    }

}
