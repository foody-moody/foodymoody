package com.foodymoody.be.common.exception;

public class ReplyNotExistsException extends BusinessException {

    public ReplyNotExistsException() {
        super(ErrorMessage.REPLY_NOT_EXISTS);
    }
}
