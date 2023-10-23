package com.foodymoody.be.comment.unit;

import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import com.foodymoody.be.common.exception.ContentIsEmptyException;
import com.foodymoody.be.common.exception.ContentIsOver200Exception;
import com.foodymoody.be.common.exception.ContentIsSpaceException;
import com.foodymoody.be.common.exception.ContentNotExistsException;
import com.foodymoody.be.common.exception.FeedIdNotExistsException;

public class CommentValidator {

    private CommentValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static void validate(RegisterCommentRequest request) {
        if (isNull(request)) {
            throw new ContentNotExistsException();
        }
        if (isEmpty(request)) {
            throw new ContentIsEmptyException();
        }
        if (isBlank(request)) {
            throw new ContentIsSpaceException();
        }
        if (isOver200(request)) {
            throw new ContentIsOver200Exception();
        }
        if (isZero(request)) {
            throw new FeedIdNotExistsException();
        }
    }

    private static boolean isZero(RegisterCommentRequest request) {
        return request.getFeedId() == 0;
    }

    private static boolean isOver200(RegisterCommentRequest request) {
        return request.getContent().length() > 200;
    }

    private static boolean isBlank(RegisterCommentRequest request) {
        return request.getContent().isBlank();
    }

    private static boolean isEmpty(RegisterCommentRequest request) {
        return request.getContent().isEmpty();
    }

    private static boolean isNull(RegisterCommentRequest request) {
        return request.getContent() == null;
    }
}
