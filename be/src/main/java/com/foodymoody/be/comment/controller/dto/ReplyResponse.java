package com.foodymoody.be.comment.controller.dto;

import java.time.LocalDateTime;

public class ReplyResponse {

    private String id;
    private String content;
    private MemberResponse member;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReplyResponse(String id, String content, String memberId, String memberName, String memberProfileImage,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.member = new MemberResponse(memberId, memberProfileImage, memberName);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public MemberResponse getMember() {
        return member;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
