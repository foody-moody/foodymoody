package com.foodymoody.be.comment.util;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import com.foodymoody.be.common.exception.ContentIsEmptyException;
import com.foodymoody.be.common.exception.ContentIsOver200Exception;
import com.foodymoody.be.common.exception.ContentIsSpaceException;
import com.foodymoody.be.common.exception.ContentNotExistsException;
import com.foodymoody.be.common.exception.FeedIdNotExistsException;
import com.foodymoody.be.common.exception.RegisterCommentRequestNotNullException;

public class CommentValidator {

    public static final int COUNT_MAX_SIZE = 200;
    public static final int ZERO = 0;

    private CommentValidator() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static void validate(RegisterCommentRequest request) {
        if (request == null) {
            throw new RegisterCommentRequestNotNullException();
        }
        String content = request.getContent();
        if (isNull(content)) {
            throw new ContentNotExistsException();
        }
        if (isEmpty(content)) {
            throw new ContentIsEmptyException();
        }
        if (isBlank(content)) {
            throw new ContentIsSpaceException();
        }
        if (isOver200(content)) {
            throw new ContentIsOver200Exception();
        }
        long feedId = request.getFeedId();
        if (isZero(feedId)) {
            throw new FeedIdNotExistsException();
        }
    }

    private static boolean isZero(long feedId) {
        return feedId == ZERO;
    }

    private static boolean isOver200(String content) {
        return content.length() > COUNT_MAX_SIZE;
    }

    private static boolean isBlank(String content) {
        return content.isBlank();
    }

    private static boolean isEmpty(String content) {
        return content.isEmpty();
    }

    private static boolean isNull(String content) {
        return content == null;
    }
}
