package com.foodymoody.be.comment.util;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.common.exception.ContentIsEmptyException;
import com.foodymoody.be.common.exception.ContentIsOver200Exception;
import com.foodymoody.be.common.exception.ContentIsSpaceException;
import com.foodymoody.be.common.exception.ContentNotExistsException;
import com.foodymoody.be.common.exception.InvalidIdException;

public class CommentValidator {

    public static final int COUNT_MAX_SIZE = 200;
    public static final int ZERO = 0;

    private CommentValidator() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static boolean isZero(long feedId) {
        return feedId == ZERO;
    }

    public static boolean isOver200(String content) {
        return content.length() > COUNT_MAX_SIZE;
    }

    public static boolean isBlank(String content) {
        return content.isBlank();
    }

    public static boolean isEmpty(String content) {
        return content.isEmpty();
    }

    public static boolean isNull(String content) {
        return content == null;
    }

    public static void validate(String id, String content, long feedId) {
        validateId(id);
        validateContent(content);
        if (isZero(feedId)) {
            throw new InvalidIdException();
        }
    }

    public static void validateContent(String content) {
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
    }

    public static void validateId(String id) {
        if (isNull(id)) {
            throw new InvalidIdException();
        }
    }
}
