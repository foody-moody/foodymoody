package com.foodymoody.be.common.exception;

public class FeedCommentNotFoundException extends BusinessException {

    public FeedCommentNotFoundException() {
        super(ErrorMessage.FEED_COMMENT_NOT_FOUND);
    }

}
