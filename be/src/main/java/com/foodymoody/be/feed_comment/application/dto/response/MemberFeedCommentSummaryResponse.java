package com.foodymoody.be.feed_comment.application.dto.response;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class MemberFeedCommentSummaryResponse {

    private final FeedCommentId id;
    private final Content content;
    private final MemberSummaryResponse member;
    private final boolean hasReply;
    private final long replyCount;
    private final long likeCount;
    private final boolean liked;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public MemberFeedCommentSummaryResponse(
            FeedCommentId id,
            Content content,
            MemberSummaryResponse member,
            boolean hasReply,
            long replyCount,
            long likeCount,
            boolean liked,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.member = member;
        this.hasReply = hasReply;
        this.replyCount = replyCount;
        this.likeCount = likeCount;
        this.liked = liked;
    }
}
