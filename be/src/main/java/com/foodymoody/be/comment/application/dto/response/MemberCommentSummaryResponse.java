package com.foodymoody.be.comment.application.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class MemberCommentSummaryResponse {

    private String id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private MemberSummaryResponse member;
    private boolean hasReply;
    private long replyCount;
    private long heartCount;
    private boolean hearted;

    public MemberCommentSummaryResponse(String id, String content, LocalDateTime createdAt, LocalDateTime updatedAt,
            MemberSummaryResponse member, boolean hasReply, long replyCount, long heartCount, boolean hearted) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.member = member;
        this.hasReply = hasReply;
        this.replyCount = replyCount;
        this.heartCount = heartCount;
        this.hearted = hearted;
    }
}
