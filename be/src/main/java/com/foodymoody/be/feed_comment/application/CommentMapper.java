package com.foodymoody.be.feed_comment.application;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.feed_comment.application.dto.data.RegisterCommentData;
import com.foodymoody.be.feed_comment.application.dto.data.RegisterReplyData;
import com.foodymoody.be.feed_comment.application.dto.response.MemberCommentSummary;
import com.foodymoody.be.feed_comment.application.dto.response.MemberCommentSummaryResponse;
import com.foodymoody.be.feed_comment.application.dto.response.MemberReplySummary;
import com.foodymoody.be.feed_comment.application.dto.response.MemberReplySummaryResponse;
import com.foodymoody.be.feed_comment.application.dto.response.MemberSummaryResponse;
import com.foodymoody.be.feed_comment.domain.entity.FeedComment;
import com.foodymoody.be.feed_comment.domain.entity.FeedReply;
import java.time.LocalDateTime;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public static Slice<MemberCommentSummaryResponse> mapToSummaryResponse(
            Slice<MemberCommentSummary> withMemberAllByFeedId
    ) {
        return withMemberAllByFeedId.map(
                summary -> new MemberCommentSummaryResponse(
                        summary.getId(),
                        summary.getContent(),
                        summary.getCreatedAt(),
                        summary.getUpdatedAt(),
                        new MemberSummaryResponse(summary.getMemberId(), summary.getNickname(), summary.getImageUrl()),
                        summary.isHasReply(),
                        summary.getReplyCount(),
                        summary.getHeartCount(),
                        summary.isHearted()
                )
        );
    }

    public FeedComment toEntity(
            RegisterCommentData data,
            FeedCommentId feedCommentId,
            LocalDateTime createdAt
    ) {
        return new FeedComment(
                feedCommentId, data.getContent(), data.getFeedId(), false, data.getMemberId(), createdAt);
    }

    public FeedReply toReply(FeedReplyId feedReplyId, RegisterReplyData data, LocalDateTime now) {
        var content = data.getContent();
        var memberId = data.getMemberId();
        return new FeedReply(feedReplyId, content, false, memberId, now, now);
    }

    public Slice<MemberReplySummaryResponse> toReplySummaryResponse(Slice<MemberReplySummary> memberReplySummaries) {
        return memberReplySummaries.map(
                summary -> new MemberReplySummaryResponse(
                        summary.getReplyId(),
                        summary.getContent(),
                        summary.getCreatedAt(),
                        summary.getUpdatedAt(),
                        new MemberSummaryResponse(summary.getMemberId(), summary.getNickname(), summary.getImageUrl()),
                        summary.getHeartCount(), summary.isHearted()
                )
        );
    }
}
