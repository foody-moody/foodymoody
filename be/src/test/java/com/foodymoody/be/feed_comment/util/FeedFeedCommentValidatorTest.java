package com.foodymoody.be.feed_comment.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.foodymoody.be.common.exception.ContentNotExistsException;
import com.foodymoody.be.common.exception.CreateTimeIsNullException;
import com.foodymoody.be.common.exception.InvalidCommentIdException;
import com.foodymoody.be.common.exception.InvalidFeedIdException;
import com.foodymoody.be.common.exception.InvalidMemberIdException;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.domain.entity.FeedCommentValidator;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("댓글 유효성 검사 테스트")
class FeedFeedCommentValidatorTest {

    FeedCommentId feedCommentId;
    Content content;
    FeedId feedId;
    MemberId memberId;
    LocalDateTime createdAt;

    @BeforeEach
    void setUp() {
        feedCommentId = CommentFixture.commentId();
        content = CommentFixture.content();
        memberId = CommentFixture.memberId();
        createdAt = CommentFixture.newLocalTime();
        feedId = CommentFixture.feedId();
    }

    @Test
    @DisplayName("피드 아이디가 null이면 예외가 발생한다.")
    void when_feed_id_is_null_then_throw_exception() {
        // when,then
        assertThatThrownBy(() -> FeedCommentValidator.validate(
                feedCommentId,
                content,
                null,
                memberId,
                createdAt
        ))
                .isInstanceOf(InvalidFeedIdException.class);
    }

    @Test
    @DisplayName("댓글 내용이 null이면 예외가 발생한다.")
    void when_content_is_null_then_throw_exception() {
        // when,then
        assertThatThrownBy(() -> FeedCommentValidator.validate(
                feedCommentId,
                null,
                feedId,
                memberId,
                createdAt
        ))
                .isInstanceOf(ContentNotExistsException.class);
    }

    @Test
    @DisplayName("댓글 작성자 아이디가 null이면 예외가 발생한다.")
    void when_member_id_is_null_then_throw_exception() {
        // when,then
        assertThatThrownBy(() -> FeedCommentValidator.validate(
                feedCommentId,
                content,
                feedId,
                null,
                createdAt
        ))
                .isInstanceOf(InvalidMemberIdException.class);
    }

    @Test
    @DisplayName("댓글 작성 시간이 null이면 예외가 발생한다.")
    void when_created_at_is_null_then_throw_exception() {
        // when,then
        assertThatThrownBy(() -> FeedCommentValidator.validate(
                feedCommentId,
                content,
                feedId,
                memberId,
                null
        ))
                .isInstanceOf(CreateTimeIsNullException.class);
    }

    @Test
    @DisplayName("댓글 아이디가 null이면 예외가 발생한다.")
    void when_comment_id_is_null_then_throw_exception() {
        // when,then
        assertThatThrownBy(() -> FeedCommentValidator.validate(
                null,
                content,
                feedId,
                memberId,
                createdAt
        ))
                .isInstanceOf(InvalidCommentIdException.class);
    }
}
