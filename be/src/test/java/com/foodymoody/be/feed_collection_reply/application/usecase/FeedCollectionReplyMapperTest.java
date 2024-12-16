package com.foodymoody.be.feed_collection_reply.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplySummary;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedCollectionReplyMapperTest {

    @DisplayName("Summary를 Response로 변환한다")
    @Test
    void toResponse() {
        // given
        var summary = new FeedCollectionReplySummaryImpl(
                IdFactory.createFeedCollectionReplyId(),
                new Content("content"),
                LocalDateTime.now(),
                LocalDateTime.now(),
                IdFactory.createMemberId(),
                "nickname",
                "profileUrl",
                true,
                1
        );

        // when
        var response = FeedCollectionReplyMapper.toResponse(summary);

        // then
        Assertions.assertAll(
                () -> assertThat(response.getId()).isEqualTo(summary.getId()),
                () -> assertThat(response.getContent()).isEqualTo(summary.getContent()),
                () -> assertThat(response.getCreatedAt()).isEqualTo(summary.getCreatedAt()),
                () -> assertThat(response.getUpdatedAt()).isEqualTo(summary.getUpdatedAt()),
                () -> assertThat(response.getMember().getMemberId()).isEqualTo(summary.getMemberId()),
                () -> assertThat(response.getMember().getNickname()).isEqualTo(summary.getNickname()),
                () -> assertThat(response.getMember().getProfileUrl()).isEqualTo(summary.getProfileUrl()),
                () -> assertThat(response.isLiked()).isEqualTo(summary.isLiked()),
                () -> assertThat(response.getLikeCount()).isEqualTo(summary.getLikeCount())
        );
    }

    class FeedCollectionReplySummaryImpl implements FeedCollectionReplySummary {

        FeedCollectionReplyId id;
        Content content;
        LocalDateTime updatedAt;
        LocalDateTime createdAt;
        MemberId memberId;
        String nickname;
        String profileUrl;
        boolean liked;
        long likeCount;

        public FeedCollectionReplySummaryImpl(
                FeedCollectionReplyId id, Content content, LocalDateTime updatedAt, LocalDateTime createdAt,
                MemberId memberId,
                String nickname, String profileUrl, boolean liked, long likeCount
        ) {
            this.id = id;
            this.content = content;
            this.updatedAt = updatedAt;
            this.createdAt = createdAt;
            this.memberId = memberId;
            this.nickname = nickname;
            this.profileUrl = profileUrl;
            this.liked = liked;
            this.likeCount = likeCount;
        }

        @Override
        public FeedCollectionReplyId getId() {
            return id;
        }

        @Override
        public Content getContent() {
            return content;
        }

        @Override
        public LocalDateTime getUpdatedAt() {
            return updatedAt;
        }

        @Override
        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        @Override
        public MemberId getMemberId() {
            return memberId;
        }

        @Override
        public String getNickname() {
            return nickname;
        }

        @Override
        public String getProfileUrl() {
            return profileUrl;
        }

        @Override
        public boolean isLiked() {
            return liked;
        }

        @Override
        public long getLikeCount() {
            return likeCount;
        }

    }

}
