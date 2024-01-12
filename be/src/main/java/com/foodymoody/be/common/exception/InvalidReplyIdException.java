package com.foodymoody.be.common.exception;

public class InvalidReplyIdException extends BusinessException {

    public InvalidReplyIdException() {
        super(ErrorMessage.INVALID_REPLY_ID);
    }
}
