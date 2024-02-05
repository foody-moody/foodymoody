package com.foodymoody.be.feed_comment.application.dto.response;

import com.foodymoody.be.common.util.Content;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class MemberCommentSummaryResponse {

    private String id;
    private Content content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private MemberSummaryResponse member;
    private boolean hasReply;
    private long replyCount;
    private long likeCount;
    private boolean liked;

    public MemberCommentSummaryResponse(
            String id, Content content, LocalDateTime createdAt, LocalDateTime updatedAt,
            MemberSummaryResponse member, boolean hasReply, long replyCount, long likeCount, boolean liked) {
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
