package com.foodymoody.be.comment.application.dto.response;

import java.time.LocalDateTime;

public class MemberReplySummaryResponse {

    private String id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private MemberSummaryResponse member;

    public MemberReplySummaryResponse(String id, String content, LocalDateTime createdAt, LocalDateTime updatedAt,
            MemberSummaryResponse member) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.member = member;
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
}
