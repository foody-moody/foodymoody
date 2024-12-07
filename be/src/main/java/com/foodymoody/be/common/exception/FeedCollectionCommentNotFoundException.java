package com.foodymoody.be.common.exception;

public class FeedCollectionCommentNotFoundException extends BusinessException {

    public FeedCollectionCommentNotFoundException() {
        super(ErrorMessage.FEED_COLLECTION_COMMENT_NOT_FOUND);
    }

}
