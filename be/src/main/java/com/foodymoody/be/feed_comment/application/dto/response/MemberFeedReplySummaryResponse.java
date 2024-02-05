package com.foodymoody.be.feed_comment.application.dto.response;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class MemberFeedReplySummaryResponse {

    private final FeedReplyId id;
    private final Content content;
    private final MemberSummaryResponse member;
    private final long likeCount;
    private final boolean liked;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public MemberFeedReplySummaryResponse(
            FeedReplyId id,
            Content content,
            MemberSummaryResponse member,
            long likeCount,
            boolean liked,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.content = content;
        this.member = member;
        this.likeCount = likeCount;
        this.liked = liked;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
