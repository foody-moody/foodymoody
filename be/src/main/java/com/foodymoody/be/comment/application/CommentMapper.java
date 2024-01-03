package com.foodymoody.be.comment.application;

import com.foodymoody.be.comment.application.dto.request.RegisterCommentRequest;
import com.foodymoody.be.comment.application.dto.response.MemberCommentSummary;
import com.foodymoody.be.comment.application.dto.response.MemberCommentSummaryResponse;
import com.foodymoody.be.comment.application.dto.response.MemberReplySummary;
import com.foodymoody.be.comment.application.dto.response.MemberReplySummaryResponse;
import com.foodymoody.be.comment.application.dto.response.MemberSummaryResponse;
import com.foodymoody.be.comment.domain.entity.Comment;
import com.foodymoody.be.comment.domain.entity.Reply;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.ReplyId;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public static Slice<MemberCommentSummaryResponse> mapToSummaryResponse(
            Slice<MemberCommentSummary> withMemberAllByFeedId) {
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

    public Comment toEntity(RegisterCommentRequest request, FeedId feedId, LocalDateTime createdAt, CommentId commentId,
            MemberId memberId) {
        return new Comment(commentId, request.getContent(), feedId, false, memberId, createdAt);
    }

    public Reply toReply(ReplyId replyId, LocalDateTime now, MemberId memberId, @NotNull @NotBlank String content) {
        return new Reply(replyId, content, false, memberId, now, now);
    }

    public Slice<MemberReplySummaryResponse> toReplySummaryResponse(Slice<MemberReplySummary> memberReplySummaries) {
        return memberReplySummaries.map(
                summary -> new MemberReplySummaryResponse(
                        summary.getReplyId(),
                        summary.getContent(),
                        summary.getCreatedAt(),
                        summary.getUpdatedAt(),
                        new MemberSummaryResponse(summary.getMemberId(), summary.getNickname(), summary.getImageUrl()),
                        summary.getHeartCount(), summary.isHearted())
        );
    }
}
