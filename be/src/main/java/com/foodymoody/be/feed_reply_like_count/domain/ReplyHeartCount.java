package com.foodymoody.be.feed_reply_like_count.domain;

import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.ReplyHeartCountId;
import javax.persistence.AttributeOverride;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ReplyHeartCount {

    @EmbeddedId
    private ReplyHeartCountId id;
    @AttributeOverride(name = "value", column = @javax.persistence.Column(name = "reply_id"))
    private FeedReplyId feedReplyId;
    private long count;
    @Version
    private Long version;

    public ReplyHeartCount(ReplyHeartCountId id, FeedReplyId feedReplyId, long count) {
        this.id = id;
        this.feedReplyId = feedReplyId;
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
