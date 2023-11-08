package com.foodymoody.be.comment.controller.dto;

import java.time.LocalDateTime;

public class CommentResponse {

    private String id;
    private String content;
    private boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private CommentResponse(String id, String content, boolean deleted, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static CommentResponse of(String id, String content, boolean deleted, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new CommentResponse(id, content, deleted, createdAt, updatedAt);
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
