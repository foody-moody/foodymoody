package com.foodymoody.be.feed_comment_like.application.exception;

import com.foodymoody.be.common.exception.BusinessException;
import com.foodymoody.be.common.exception.ErrorMessage;

public class FeedCommentIsAlreadyLikedException extends BusinessException {

    public FeedCommentIsAlreadyLikedException() {
        super(ErrorMessage.FEED_COMMENT_IS_ALREADY_LIKED);
    }

}
