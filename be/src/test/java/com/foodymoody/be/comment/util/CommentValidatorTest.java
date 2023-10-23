package com.foodymoody.be.comment.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.foodymoody.be.common.exception.ContentIsEmptyException;
import com.foodymoody.be.common.exception.ContentIsOver200Exception;
import com.foodymoody.be.common.exception.ContentIsSpaceException;
import com.foodymoody.be.common.exception.ContentNotExistsException;
import com.foodymoody.be.common.exception.FeedIdNotExistsException;
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

    @DisplayName("RegisterCommentRequest의 content가 null이면 ContentNotExistsException이 발생한다.")
    @Test
    void if_registerCommentRequest_content_is_null_then_throw_contentNotExistsException() {
        assertThatThrownBy(() -> CommentValidator.validate(CommentFixture.registerCommentRequestWithoutContent()))
                .isInstanceOf(ContentNotExistsException.class);
    }

    @DisplayName("RegisterCommentRequest의 content가 empty이면 CContentIsEmptyException이 발생한다.")
    @Test
    void if_registerCommentRequest_content_is_empty_then_throw_contentNotExistsException() {
        assertThatThrownBy(() -> CommentValidator.validate(CommentFixture.registerCommentRequestWithEmptyContent()))
                .isInstanceOf(ContentIsEmptyException.class);
    }

    @DisplayName("RegisterCommentRequest의 content가 space이면 ContentIsSpaceException이 발생한다.")
    @Test
    void if_registerCommentRequest_content_is_space_then_throw_contentNotExistsException() {
        assertThatThrownBy(() -> CommentValidator.validate(CommentFixture.registerCommentRequestWithSpace()))
                .isInstanceOf(ContentIsSpaceException.class);
    }

    @DisplayName("RegisterCommentRequest의 content가 200자를 초과하면 ContentIsOver200Exception이 발생한다.")
    @Test
    void if_registerCommentRequest_content_is_over_200_then_throw_contentNotExistsException() {
        assertThatThrownBy(() -> CommentValidator.validate(CommentFixture.registerCommentRequestWithContentOver200()))
                .isInstanceOf(ContentIsOver200Exception.class);
    }

    @DisplayName("RegisterCommentRequest의 feedId가 0이면 FeedIdNotExistsException이 발생한다.")
    @Test
    void if_registerCommentRequest_feedId_is_zero_then_throw_feedIdNotExistsException() {
        assertThatThrownBy(() -> CommentValidator.validate(CommentFixture.registerCommentRequestWithoutFeedId()))
                .isInstanceOf(FeedIdNotExistsException.class);
    }

    @DisplayName("RegisterCommentRequest가 유효하면 예외가 발생하지 않는다.")
    @Test
    void if_registerCommentRequest_is_valid_then_no_exception() {
        CommentValidator.validate(CommentFixture.registerCommentRequest());
    }
}
