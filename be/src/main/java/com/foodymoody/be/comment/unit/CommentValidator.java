package com.foodymoody.be.comment.unit;

import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import com.foodymoody.be.common.exception.ContentIsEmptyException;
import com.foodymoody.be.common.exception.ContentIsOver200Exception;
import com.foodymoody.be.common.exception.ContentIsSpaceException;
import com.foodymoody.be.common.exception.ContentNotExistsException;
import com.foodymoody.be.common.exception.FeedIdNotExistsException;
import com.foodymoody.be.common.exception.RegisterCommentRequestNotNullException;

public class CommentValidator {

    private CommentValidator() {
        throw new IllegalStateException("Utility class");
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
        return feedId == 0;
    }

    private static boolean isOver200(String content) {
        return content.length() > 200;
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
