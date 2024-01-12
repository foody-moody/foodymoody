package com.foodymoody.be.common.exception;

public class ContentNotExistsException extends BusinessException {

    public ContentNotExistsException() {
        super(ErrorMessage.COMMENT_NOT_EXISTS);
    }
}
