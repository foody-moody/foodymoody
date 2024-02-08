package com.foodymoody.be.common.exception;

public class FeedReplyNotFoundException extends BusinessException {

    public FeedReplyNotFoundException() {
        super(ErrorMessage.REPLY_NOT_EXISTS);
    }
}
