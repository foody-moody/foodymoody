package com.foodymoody.be.common.exception;

public class FeedNotificationNotFoundException extends BusinessException {

    public FeedNotificationNotFoundException() {
        super(ErrorMessage.FEED_NOTIFICATION_NOT_FOUND);
    }

}
