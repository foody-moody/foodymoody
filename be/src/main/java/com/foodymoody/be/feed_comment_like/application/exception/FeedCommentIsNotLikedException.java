package com.foodymoody.be.feed_comment_like.application.exception;

import com.foodymoody.be.common.exception.BusinessException;
import com.foodymoody.be.common.exception.ErrorMessage;

public class FeedCommentIsNotLikedException extends BusinessException {

    public FeedCommentIsNotLikedException() {
        super(ErrorMessage.FEED_COMMENT_IS_NOT_LIKED);
    }
}
