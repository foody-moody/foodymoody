package com.foodymoody.be.comment.domain.entity;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.ReplyId;
import com.foodymoody.be.common.validator.ContentValidator;
import com.foodymoody.be.common.validator.CreatedAtValidator;
import com.foodymoody.be.common.validator.MemberIdValidator;
import com.foodymoody.be.common.validator.ReplyIdValidator;
import java.time.LocalDateTime;

public class ReplyValidator {

    private ReplyValidator() {
        throw new AssertionError(UTILITY_CLASS);
    }

    public static void validate(ReplyId replyId, Content content, MemberId memberId, LocalDateTime createdAt) {
        ReplyIdValidator.validate(replyId);
        ContentValidator.validate(content);
        MemberIdValidator.validate(memberId);
        CreatedAtValidator.validate(createdAt);
    }

}
