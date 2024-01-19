package com.foodymoody.be.feed_comment.domain.entity;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.validator.CommentIdValidator;
import com.foodymoody.be.common.validator.ContentValidator;
import com.foodymoody.be.common.validator.CreatedAtValidator;
import com.foodymoody.be.common.validator.FeedIdValidator;
import com.foodymoody.be.common.validator.MemberIdValidator;
import java.time.LocalDateTime;

public class FeedCommentValidator {


    private FeedCommentValidator() {
        throw new AssertionError(UTILITY_CLASS);
    }

    public static void validate(
            FeedCommentId id,
            Content content,
            FeedId feedId,
            MemberId memberId,
            LocalDateTime createdAt
    ) {
        CommentIdValidator.validate(id);
        ContentValidator.validate(content);
        FeedIdValidator.validate(feedId);
        MemberIdValidator.validate(memberId);
        CreatedAtValidator.validate(createdAt);
    }
}
