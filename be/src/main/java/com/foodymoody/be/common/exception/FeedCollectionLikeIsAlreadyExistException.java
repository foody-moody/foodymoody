package com.foodymoody.be.common.exception;

public class FeedCollectionLikeIsAlreadyExistException extends BusinessException {

    public FeedCollectionLikeIsAlreadyExistException() {
        super(ErrorMessage.FEED_COLLECTION_LIKE_IS_ALREADY_EXIST);
    }
}
