package com.foodymoody.be.comment.domain.entity;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.common.exception.ContentNotExistsException;
import com.foodymoody.be.common.exception.CreateTimeIsNullException;
import com.foodymoody.be.common.exception.InvalidIdException;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;

public class CommentValidator {


    private CommentValidator() {
        throw new AssertionError(UTILITY_CLASS);
    }

    public static void validate(
            CommentId id,
            Content content,
            FeedId feedId,
            MemberId memberId,
            LocalDateTime createdAt
    ) {
        validateId(id);
        validateContent(content);
        validateFeedId(feedId);
        validateMemberId(memberId);
        validateCreatedAt(createdAt);
    }

    public static void validateMemberId(MemberId id) {
        if (id == null) {
            throw new InvalidIdException();
        }
    }

    public static void validateId(CommentId id) {
        if (id == null) {
            throw new InvalidIdException();
        }
    }

    public static void validateContent(Content content) {
        if (content == null) {
            throw new ContentNotExistsException();
        }
    }

    public static void validateFeedId(FeedId feedId) {
        if (feedId == null) {
            throw new InvalidIdException();
        }
    }

    public static void validateCreatedAt(LocalDateTime createdAt) {
        if (createdAt == null) {
            throw new CreateTimeIsNullException();
        }
    }
}
