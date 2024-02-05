package com.foodymoody.be.feed_collection_comment.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.foodymoody.be.common.exception.PermissionDeniedAccessException;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.IdFactory;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedCollectionCommentTest {

    @DisplayName("피드 컬렉션 댓글 삭제 권한이 없는 경우 예외가 발생한다.")
    @Test
    void when_delete_without_permission() {
        // given
        var rightMemberId = IdFactory.createMemberId();
        var wrongMemberId = IdFactory.createMemberId();
        var createdAt = LocalDateTime.now();
        var updatedAt = LocalDateTime.now();
        var content = new Content("댓글 내용");
        var feedCollectionComment = new FeedCollectionComment(
                IdFactory.createFeedCollectionCommentId(),
                IdFactory.createFeedCollectionId(),
                rightMemberId,
                content,
                createdAt
        );

        // when, then
        assertThatThrownBy(() -> feedCollectionComment.delete(wrongMemberId, updatedAt))
                .isInstanceOf(PermissionDeniedAccessException.class);
    }

    @DisplayName("피드 컬렉션 댓글 삭제 권한이 있는 경우 삭제된다.")
    @Test
    void when_delete_with_permission() {
        // given
        var memberId = IdFactory.createMemberId();
        var createdAt = LocalDateTime.now();
        var updatedAt = LocalDateTime.now();
        var content = new Content("댓글 내용");
        var feedCollectionComment = new FeedCollectionComment(
                IdFactory.createFeedCollectionCommentId(),
                IdFactory.createFeedCollectionId(),
                memberId,
                content,
                createdAt
        );

        // when
        feedCollectionComment.delete(memberId, updatedAt);

        // then
        assertThat(feedCollectionComment).hasFieldOrPropertyWithValue("deleted", true)
                .hasFieldOrPropertyWithValue("updatedAt", updatedAt);
    }

    @DisplayName("피드 컬렉션 댓글 수정 권한이 없는 경우 예외가 발생한다.")
    @Test
    void when_update_without_permission() {
        // given
        var rightMemberId = IdFactory.createMemberId();
        var wrongMemberId = IdFactory.createMemberId();
        var createdAt = LocalDateTime.now();
        var updatedAt = LocalDateTime.now();
        var content = new Content("댓글 내용");
        var feedCollectionComment = new FeedCollectionComment(
                IdFactory.createFeedCollectionCommentId(),
                IdFactory.createFeedCollectionId(),
                rightMemberId,
                content,
                createdAt
        );

        // when, then
        assertThatThrownBy(() -> feedCollectionComment.update(content, wrongMemberId, updatedAt))
                .isInstanceOf(PermissionDeniedAccessException.class);
    }

    @DisplayName("피드 컬렉션 댓글 수정 권한이 있는 경우 수정된다.")
    @Test
    void when_update_with_permission() {
        // given
        var memberId = IdFactory.createMemberId();
        var createdAt = LocalDateTime.now();
        var content = new Content("댓글 내용");
        var feedCollectionComment = new FeedCollectionComment(
                IdFactory.createFeedCollectionCommentId(),
                IdFactory.createFeedCollectionId(),
                memberId,
                content,
                createdAt
        );
        var updatedContent = new Content("수정된 댓글 내용");
        var updatedAt = LocalDateTime.now();

        // when
        feedCollectionComment.update(updatedContent, memberId, updatedAt);

        // then
        assertThat(feedCollectionComment).hasFieldOrPropertyWithValue("content", updatedContent)
                .hasFieldOrPropertyWithValue("updatedAt", updatedAt);
    }
}
