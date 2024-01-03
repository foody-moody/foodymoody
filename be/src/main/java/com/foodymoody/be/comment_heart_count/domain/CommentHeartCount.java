package com.foodymoody.be.comment_heart_count.domain;

import com.foodymoody.be.common.util.ids.CommentHeartCountId;
import com.foodymoody.be.common.util.ids.CommentId;
import javax.persistence.AttributeOverride;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CommentHeartCount {

    @EmbeddedId
    private CommentHeartCountId id;
    @AttributeOverride(name = "value", column = @javax.persistence.Column(name = "comment_id"))
    private CommentId commentId;
    private long count;
    @Version
    private Long version;

    public CommentHeartCount(CommentHeartCountId id, CommentId commentId, long count) {
        this.id = id;
        this.commentId = commentId;
        this.count = count;
    }

    public void increment() {
        this.count++;
    }

    public void decrement() {
        if (this.count > 0) {
            this.count--;
        }
    }

    public long getCount() {
        return this.count;
    }

    public Long getVersion() {
        return version;
    }
}
