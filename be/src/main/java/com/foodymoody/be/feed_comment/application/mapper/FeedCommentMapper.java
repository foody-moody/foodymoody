package com.foodymoody.be.feed_comment.application.mapper;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.feed_comment.application.dto.data.RegisterFeedCommentData;
import com.foodymoody.be.feed_comment.application.dto.data.RegisterFeedReplyData;
import com.foodymoody.be.feed_comment.application.dto.response.MemberFeedCommentSummaryResponse;
import com.foodymoody.be.feed_comment.application.dto.response.MemberFeedReplySummaryResponse;
import com.foodymoody.be.feed_comment.application.dto.response.MemberSummaryResponse;
import com.foodymoody.be.feed_comment.domain.entity.FeedComment;
import com.foodymoody.be.feed_comment.domain.entity.FeedReply;
import com.foodymoody.be.feed_comment.domain.entity.MemberFeedCommentSummary;
import com.foodymoody.be.feed_comment.domain.entity.MemberFeedReplySummary;
import java.time.LocalDateTime;
import org.springframework.data.domain.Slice;

public class FeedCommentMapper {

    private FeedCommentMapper() {
        throw new AssertionError(UTILITY_CLASS);
    }

    public static Slice<MemberFeedCommentSummaryResponse> toSummaryResponses(
            Slice<MemberFeedCommentSummary> feedCommentSummaries
    ) {
        return feedCommentSummaries.map(FeedCommentMapper::toMemberFeedCommentSummaryResponse);
    }

    public static FeedComment toFeedComment(
            RegisterFeedCommentData data,
            FeedCommentId feedCommentId,
            LocalDateTime createdAt
    ) {
        return new FeedComment(
                feedCommentId,
                data.getContent(),
                data.getFeedId(),
                false,
                data.getMemberId(),
                createdAt
        );
    }

    public static FeedReply toFeedReply(FeedReplyId feedReplyId, RegisterFeedReplyData data, LocalDateTime createdAt) {
        var content = data.getContent();
        var memberId = data.getMemberId();
        return new FeedReply(feedReplyId, content, false, memberId, createdAt, createdAt);
    }

    public static Slice<MemberFeedReplySummaryResponse> toReplySummaryResponses(
            Slice<MemberFeedReplySummary> memberReplySummaries
    ) {
        return memberReplySummaries.map(FeedCommentMapper::toMemberFeedReplySummaryResponse);
    }

    public static MemberFeedReplySummaryResponse toMemberFeedReplySummaryResponse(MemberFeedReplySummary summary) {
        return new MemberFeedReplySummaryResponse(
                summary.getReplyId(),
                summary.getContent(),
                getMember(summary),
                summary.getLikeCount(),
                summary.isLiked(),
                summary.getCreatedAt(),
                summary.getUpdatedAt()
        );
    }

    public static MemberFeedCommentSummaryResponse toMemberFeedCommentSummaryResponse(
            MemberFeedCommentSummary summary
    ) {
        return new MemberFeedCommentSummaryResponse(
                summary.getId(),
                summary.getContent(),
                getMember(summary),
                summary.isHasReply(),
                summary.getReplyCount(),
                summary.getLikeCount(),
                summary.isLiked(),
                summary.getCreatedAt(),
                summary.getUpdatedAt()
        );
    }

    public static MemberSummaryResponse getMember(MemberFeedCommentSummary summary) {
        return new MemberSummaryResponse(summary.getMemberId(), summary.getNickname(), summary.getImageUrl());
    }

    public static MemberSummaryResponse getMember(MemberFeedReplySummary summary) {
        return new MemberSummaryResponse(summary.getMemberId(), summary.getNickname(), summary.getImageUrl());
    }
}
