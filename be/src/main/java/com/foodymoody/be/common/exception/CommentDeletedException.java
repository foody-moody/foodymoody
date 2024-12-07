package com.foodymoody.be.common.exception;

public class CommentDeletedException extends BusinessException {

    public CommentDeletedException() {
        super(ErrorMessage.COMMENT_DELETED);
    }

}
