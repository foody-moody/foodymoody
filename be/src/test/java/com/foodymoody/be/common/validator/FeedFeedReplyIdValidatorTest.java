package com.foodymoody.be.common.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.foodymoody.be.common.exception.InvalidReplyIdException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedFeedReplyIdValidatorTest {

    @DisplayName("대댓글의 아이디가 null이면 예외가 발생한다")
    @Test
    void validate_when_reply_id_is_null_then_throw_exception() {
        assertThatThrownBy(() -> ReplyIdValidator.validate(null))
                .isInstanceOf(InvalidReplyIdException.class);
    }
}
