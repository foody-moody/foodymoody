package com.foodymoody.be.comment.domain.entity;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.ReplyId;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Reply {

    @Getter
    @EmbeddedId
    private ReplyId id;
    @Getter
    private Content content;
    @Getter
    private boolean deleted;
    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    @Getter
    private LocalDateTime createdAt;
    @Getter
    private LocalDateTime updatedAt;

    public Reply(
            ReplyId replyId, Content content, boolean deleted, MemberId memberId,
            LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.id = replyId;
        this.content = content;
        this.deleted = deleted;
        this.memberId = memberId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reply)) {
            return false;
        }
        Reply reply = (Reply) o;
        return Objects.equals(getId(), reply.getId());
    }
}
