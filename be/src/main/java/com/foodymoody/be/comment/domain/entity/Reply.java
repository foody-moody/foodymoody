package com.foodymoody.be.comment.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Reply {

    @EmbeddedId
    private ReplyId id;
    private String content;
    private boolean deleted;
    private String memberId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Reply(ReplyId replyId, String content, boolean deleted, String memberId,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = replyId;
        this.content = content;
        this.deleted = deleted;
        this.memberId = memberId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ReplyId getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getMemberId() {
        return memberId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
