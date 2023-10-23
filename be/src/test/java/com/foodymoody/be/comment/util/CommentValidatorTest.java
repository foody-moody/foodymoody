package com.foodymoody.be.comment.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.foodymoody.be.common.exception.RegisterCommentRequestNotNullException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("댓글 유효성 검사 테스트")
class CommentValidatorTest {

    @DisplayName("RegisterCommentRequest가 null이면 ContentNotExistsException이 발생한다.")
    @Test
    void if_registerCommentRequest_is_null_then_throw_contentNotExistsException() {
        assertThatThrownBy(() -> CommentValidator.validate(null))
                .isInstanceOf(RegisterCommentRequestNotNullException.class);
    }
}
