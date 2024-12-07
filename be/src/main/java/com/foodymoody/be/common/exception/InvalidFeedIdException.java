package com.foodymoody.be.common.exception;

public class InvalidFeedIdException extends BusinessException {

    public InvalidFeedIdException() {
        super(ErrorMessage.INVALID_FEED_ID);
    }

}
