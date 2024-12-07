package com.foodymoody.be.common.exception;

public class FeedCollectionNotFoundException extends BusinessException {

    public FeedCollectionNotFoundException() {
        super(ErrorMessage.FEED_COLLECTION_NOT_FOUND);
    }

}
