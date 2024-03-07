package com.foodymoody.be.common.exception;

public class FeedCollectionLikeIsNotExistException extends BusinessException {

    public FeedCollectionLikeIsNotExistException() {
        super(ErrorMessage.FEED_COLLECTION_LIKE_IS_NOT_EXIST);
    }

}
