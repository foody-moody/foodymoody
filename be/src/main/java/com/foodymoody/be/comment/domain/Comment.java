package com.foodymoody.be.comment.domain;

import java.time.LocalDateTime;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {

    @EmbeddedId
    private CommentId id;
    private String content;
    private String feedId;
    private boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Comment(CommentId id, String content, String feedId, LocalDateTime createdAt) {
        CommentValidator.validate(id, content, feedId, createdAt);
        this.id = id;
        this.content = content;
        this.feedId = feedId;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    public CommentId getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getFeedId() {
        return feedId;
    }

    public void edit(String content, LocalDateTime updatedAt) {
        CommentValidator.validateContent(content);
        this.content = content;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void delete(LocalDateTime deletedAt) {
        this.deleted = true;
        this.updatedAt = deletedAt;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
