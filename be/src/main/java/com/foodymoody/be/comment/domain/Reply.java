package com.foodymoody.be.comment.domain;

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
}
