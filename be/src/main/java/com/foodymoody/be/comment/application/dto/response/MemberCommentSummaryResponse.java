package com.foodymoody.be.comment.application.dto.response;

import java.time.LocalDateTime;

public class MemberCommentSummaryResponse {

    private String id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private MemberSummaryResponse member;
    private boolean hasReply;
    private int replyCount;

    public MemberCommentSummaryResponse(String id, String content, LocalDateTime createdAt, LocalDateTime updatedAt,
            MemberSummaryResponse member, boolean hasReply, int replyCount) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.member = member;
        this.hasReply = hasReply;
        this.replyCount = replyCount;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public MemberSummaryResponse getMember() {
        return member;
    }

    public boolean isHasReply() {
        return hasReply;
    }

    public int getReplyCount() {
        return replyCount;
    }
}
