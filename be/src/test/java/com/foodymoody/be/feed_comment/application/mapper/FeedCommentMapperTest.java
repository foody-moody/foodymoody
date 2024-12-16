package com.foodymoody.be.feed_comment.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.feed_comment.application.dto.response.MemberSummaryResponse;
import com.foodymoody.be.feed_comment.util.FeedCommentFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FeedCommentMapper 테스트")
class FeedCommentMapperTest {

    @DisplayName("MemberSummaryResponse 매핑 테스트")
    @Test
    void mapToSummaryResponse() {
        // given
        var feedCommentSummary = FeedCommentFixture.memberFeedCommentSummary();

        // when
        var response = FeedCommentMapper.toMemberFeedCommentSummaryResponse(feedCommentSummary);

        // then
        MemberSummaryResponse member = response.getMember();
        Assertions.assertAll(
                () -> assertThat(response.getId()).isEqualTo(feedCommentSummary.getId()),
                () -> assertThat(response.getContent()).usingRecursiveComparison()
                        .isEqualTo(feedCommentSummary.getContent()),
                () -> assertThat(response.getCreatedAt()).isEqualTo(feedCommentSummary.getCreatedAt()),
                () -> assertThat(response.getUpdatedAt()).isEqualTo(feedCommentSummary.getUpdatedAt()),
                () -> assertThat(member.getId()).isEqualTo(feedCommentSummary.getMemberId()),
                () -> assertThat(member.getNickname()).isEqualTo(feedCommentSummary.getNickname()),
                () -> assertThat(member.getImageUrl()).isEqualTo(feedCommentSummary.getImageUrl()),
                () -> assertThat(response.isHasReply()).isEqualTo(feedCommentSummary.isHasReply()),
                () -> assertThat(response.getReplyCount()).isEqualTo(feedCommentSummary.getReplyCount()),
                () -> assertThat(response.getLikeCount()).isEqualTo(feedCommentSummary.getLikeCount()),
                () -> assertThat(response.isLiked()).isEqualTo(feedCommentSummary.isLiked())
        );
    }

    @DisplayName("FeedComment 매핑 테스트")
    @Test
    void toFeedComment() {
        // given
        var registerFeedCommentData = FeedCommentFixture.registerFeedCommentData();
        var feedCommentId = FeedCommentFixture.feedCommentId();
        var createdAt = FeedCommentFixture.createdAt();

        // when
        var feedComment = FeedCommentMapper.toFeedComment(registerFeedCommentData, feedCommentId, createdAt);

        // then
        Assertions.assertAll(
                () -> assertThat(feedComment.getId()).isEqualTo(feedCommentId),
                () -> assertThat(feedComment.getContent()).isEqualTo(registerFeedCommentData.getContent()),
                () -> assertThat(feedComment.getFeedId()).isEqualTo(registerFeedCommentData.getFeedId()),
                () -> assertThat(feedComment.isDeleted()).isFalse(),
                () -> assertThat(feedComment.getMemberId()).isEqualTo(registerFeedCommentData.getMemberId()),
                () -> assertThat(feedComment.getCreatedAt()).isEqualTo(createdAt)
        );
    }

    @DisplayName("FeedReply 매핑 테스트")
    @Test
    void toFeedReply() {
        // given
        var feedReplyId = FeedCommentFixture.feedReplyId();
        var registerFeedReplyData = FeedCommentFixture.registerFeedReplyData();
        var createdAt = FeedCommentFixture.createdAt();

        // when
        var feedReply = FeedCommentMapper.toFeedReply(feedReplyId, registerFeedReplyData, createdAt);

        // then
        Assertions.assertAll(
                () -> assertThat(feedReply.getId()).isEqualTo(feedReplyId),
                () -> assertThat(feedReply.getContent()).isEqualTo(registerFeedReplyData.getContent()),
                () -> assertThat(feedReply.isDeleted()).isFalse(),
                () -> assertThat(feedReply.getMemberId()).isEqualTo(registerFeedReplyData.getMemberId()),
                () -> assertThat(feedReply.getCreatedAt()).isEqualTo(createdAt),
                () -> assertThat(feedReply.getUpdatedAt()).isEqualTo(createdAt)
        );
    }

    @DisplayName("MemberFeedReplySummaryResponse 매핑 테스트")
    @Test
    void toReplySummaryResponse() {
        // given
        var memberFeedReplySummary = FeedCommentFixture.memberFeedReplySummary();

        // when
        var response = FeedCommentMapper.toMemberFeedReplySummaryResponse(memberFeedReplySummary);

        // then
        var member = response.getMember();
        Assertions.assertAll(
                () -> assertThat(response.getId()).usingRecursiveComparison()
                        .isEqualTo(memberFeedReplySummary.getReplyId()),
                () -> assertThat(response.getContent()).usingRecursiveComparison()
                        .isEqualTo(memberFeedReplySummary.getContent()),
                () -> assertThat(response.getCreatedAt()).isEqualTo(memberFeedReplySummary.getCreatedAt()),
                () -> assertThat(response.getUpdatedAt()).isEqualTo(memberFeedReplySummary.getUpdatedAt()),
                () -> assertThat(member.getId()).isEqualTo(memberFeedReplySummary.getMemberId()),
                () -> assertThat(member.getNickname()).isEqualTo(memberFeedReplySummary.getNickname()),
                () -> assertThat(member.getImageUrl()).isEqualTo(memberFeedReplySummary.getImageUrl()),
                () -> assertThat(response.getLikeCount()).isEqualTo(memberFeedReplySummary.getLikeCount()),
                () -> assertThat(response.isLiked()).isEqualTo(memberFeedReplySummary.isLiked())
        );
    }

}
