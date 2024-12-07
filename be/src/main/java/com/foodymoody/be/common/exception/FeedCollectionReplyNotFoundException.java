package com.foodymoody.be.common.exception;

public class FeedCollectionReplyNotFoundException extends BusinessException {

    public FeedCollectionReplyNotFoundException() {
        super(ErrorMessage.FEED_COLLECTION_REPLY_NOT_FOUND);
    }

}
