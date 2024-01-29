package com.foodymoody.be.feed_like_count.domain.entity;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.FeedLikeCountId;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedLikeCount {

    @EmbeddedId
    @Getter
    private FeedLikeCountId id;

    @AttributeOverride(name = "value", column = @Column(name = "feed_id"))
    @Getter
    private FeedId feedId;

    @Getter
    private int count;

    @Version
    @Getter
    private Long version;

    public FeedLikeCount(
            FeedLikeCountId id,
            FeedId feedId,
            int count
    ) {
        this.id = id;
        this.feedId = feedId;
        this.count = count;
    }

}
