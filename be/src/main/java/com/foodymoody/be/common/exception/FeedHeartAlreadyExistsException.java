package com.foodymoody.be.common.exception;

public class FeedHeartAlreadyExistsException extends BusinessException {

    public FeedHeartAlreadyExistsException() {
        super(ErrorMessage.FEED_HEART_ALREADY_EXISTS);
    }

}
