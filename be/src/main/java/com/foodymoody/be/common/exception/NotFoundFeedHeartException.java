package com.foodymoody.be.common.exception;

public class NotFoundFeedHeartException extends BusinessException {

    public NotFoundFeedHeartException() {
        super(ErrorMessage.FEED_HEART_NOT_FOUND);
    }

}
