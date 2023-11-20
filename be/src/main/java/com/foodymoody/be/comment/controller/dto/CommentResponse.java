package com.foodymoody.be.comment.controller.dto;

import java.time.LocalDateTime;

public class CommentResponse {

    private String id;
    private String content;
    private String memberId;
    private String memberName;
    private String memberProfileImage;
    private boolean hasReply;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentResponse(String id, String content, String memberId, String memberName,
            String memberProfileImage, boolean hasReply, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberProfileImage = memberProfileImage;
        this.hasReply = hasReply;
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

    public String getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberProfileImage() {
        return memberProfileImage;
    }

    public boolean isHasReply() {
        return hasReply;
    }
}
