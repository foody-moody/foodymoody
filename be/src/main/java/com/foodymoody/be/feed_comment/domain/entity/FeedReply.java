package com.foodymoody.be.feed_comment.domain.entity;

import com.foodymoody.be.common.exception.PermissionDeniedAccessException;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
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
public class FeedReply {

    @Getter
    @EmbeddedId
    private FeedReplyId id;

    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "content"))
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

    public FeedReply(
            FeedReplyId feedReplyId, Content content, boolean deleted, MemberId memberId,
            LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        FeedReplyValidator.validate(feedReplyId, content, memberId, createdAt);
        this.id = feedReplyId;
        this.content = content;
        this.deleted = deleted;
        this.memberId = memberId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update(MemberId memberId, Content content, LocalDateTime updatedAt) {
        checkPermissionForMemberId(memberId);
        this.content = content;
        this.updatedAt = updatedAt;
    }

    public void delete(MemberId memberId, LocalDateTime updatedAt) {
        checkPermissionForMemberId(memberId);
        this.deleted = true;
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
        if (!(o instanceof FeedReply)) {
            return false;
        }
        FeedReply feedReply = (FeedReply) o;
        return Objects.equals(getId(), feedReply.getId());
    }

    private void checkPermissionForMemberId(MemberId memberId) {
        if (!memberId.equals(this.memberId)) {
            throw new PermissionDeniedAccessException();
        }
    }

}
