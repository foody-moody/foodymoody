package com.foodymoody.be.feed_collection_reply.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.foodymoody.be.common.exception.PermissionDeniedAccessException;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedCollectionReplyTest {

    MemberId memberId;
    FeedCollectionReply feedCollectionReply;
    LocalDateTime updatedAt;
    Content newContent;


    @BeforeEach
    void setUp() {
        memberId = IdFactory.createMemberId();
        feedCollectionReply = getFeedCollectionReply(memberId);
        updatedAt = LocalDateTime.now();
        newContent = new Content("new content");
    }

    @DisplayName("작성자가 댓글 삭제")
    @Test
    void delete_with_matched_member_id() {
        // when
        feedCollectionReply.delete(memberId, updatedAt);

        // then
        assertThat(feedCollectionReply)
                .hasFieldOrPropertyWithValue("deleted", true)
                .hasFieldOrPropertyWithValue("updatedAt", updatedAt);
    }

    @DisplayName("작성자가 아닌 경우 댓글 삭제 시도하면 예외 발생")
    @Test
    void delete_with_not_matched_member_id() {
        // when,then
        assertThatThrownBy(() -> feedCollectionReply.delete(IdFactory.createMemberId(), updatedAt))
                .isInstanceOf(PermissionDeniedAccessException.class);
    }

    @DisplayName("작성자가 댓글 수정")
    @Test
    void edit_with_matched_member_id() {
        // when
        feedCollectionReply.edit(newContent, memberId, updatedAt);

        // then
        assertThat(feedCollectionReply)
                .hasFieldOrPropertyWithValue("content", newContent)
                .hasFieldOrPropertyWithValue("updatedAt", updatedAt);
    }

    @DisplayName("작성자가 아닌 경우 댓글 수정 시도하면 예외 발생")
    @Test
    void edit_with_not_matched_member_id() {
        // when,then
        assertThatThrownBy(() -> feedCollectionReply.edit(newContent, IdFactory.createMemberId(), updatedAt))
                .isInstanceOf(PermissionDeniedAccessException.class);
    }

    @NotNull
    private static FeedCollectionReply getFeedCollectionReply(MemberId memberId) {
        return new FeedCollectionReply(
                IdFactory.createFeedCollectionReplyId(),
                IdFactory.createFeedCollectionCommentId(),
                memberId,
                new Content("content"),
                LocalDateTime.now()
        );
    }
}
