package com.foodymoody.be.common.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.foodymoody.be.common.exception.InvalidCommentIdException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedCommentIdValidatorTest {

    @DisplayName("댓글의 아이디가 null이면 예외가 발생한다")
    @Test
    void validate_when_comment_id_is_null_then_throw_exception() {
        assertThatThrownBy(() -> CommentIdValidator.validate(null))
                .isInstanceOf(InvalidCommentIdException.class);
    }
}
