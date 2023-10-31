package com.foodymoody.be.common.exception;

public class FeedIdNotExistsException extends BusinessException {

    public FeedIdNotExistsException() {
        super(ErrorMessage.FEED_ID_NOT_EXISTS);
    }

}
