package com.foodymoody.be.common.validator;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.common.exception.InvalidCommentIdException;
import com.foodymoody.be.common.util.ids.FeedCommentId;

public class CommentIdValidator {

    private CommentIdValidator() {
        throw new AssertionError(UTILITY_CLASS);
    }

    public static void validate(FeedCommentId feedCommentId) {
        if (feedCommentId == null) {
            throw new InvalidCommentIdException();
        }
    }
}
