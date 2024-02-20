package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.util.ids.FeedId;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class FeedIds {

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "feed_collection_feed_ids", joinColumns = @JoinColumn(name = "feed_collection_id"))
    @AttributeOverrides(
            @AttributeOverride(name = "value", column = @Column(name = "feed_id"))
    )
    @OrderColumn(name = "feed_order")
    private List<FeedId> ids = new ArrayList<>();

    public FeedIds(List<FeedId> feedIds) {
        ids = feedIds;
    }

    public List<FeedId> getIds() {
        return ids;
    }

    public void update(List<FeedId> feedIds) {
        ids = feedIds;
    }

    public void add(FeedId feedId) {
        ids.add(feedId);
    }

    public void remove(FeedId feedId) {
        ids.remove(feedId);
    }
}
