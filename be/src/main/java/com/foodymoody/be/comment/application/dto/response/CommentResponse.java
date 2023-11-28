package com.foodymoody.be.comment.application.dto.response;

import java.time.LocalDateTime;

public class CommentResponse {

    private String id;
    private String content;
    private MemberResponse member;
    private boolean hasReply;
    private int replyCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentResponse(String id, String content, String memberId, String memberName,
            String memberProfileImage, boolean hasReply, int replyCount, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.member = new MemberResponse(memberId, memberName, memberProfileImage);
        this.hasReply = hasReply;
        this.replyCount = replyCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public MemberResponse getMember() {
        return member;
    }

    public boolean isHasReply() {
        return hasReply;
    }

    public int getReplyCount() {
        return replyCount;
    }
}
