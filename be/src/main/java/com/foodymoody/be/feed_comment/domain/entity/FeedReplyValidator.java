package com.foodymoody.be.feed_comment.domain.entity;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.validator.ContentValidator;
import com.foodymoody.be.common.validator.CreatedAtValidator;
import com.foodymoody.be.common.validator.MemberIdValidator;
import com.foodymoody.be.common.validator.ReplyIdValidator;
import java.time.LocalDateTime;

public class FeedReplyValidator {

    private FeedReplyValidator() {
        throw new AssertionError(UTILITY_CLASS);
    }

    public static void validate(FeedReplyId feedReplyId, Content content, MemberId memberId, LocalDateTime createdAt) {
        ReplyIdValidator.validate(feedReplyId);
        ContentValidator.validate(content);
        MemberIdValidator.validate(memberId);
        CreatedAtValidator.validate(createdAt);
    }

}
