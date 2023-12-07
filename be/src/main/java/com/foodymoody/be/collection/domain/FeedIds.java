package com.foodymoody.be.collection.domain;

import com.foodymoody.be.common.util.ids.FeedId;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.OrderColumn;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@NoArgsConstructor
@Embeddable
public class FeedIds {

    @BatchSize(size = 50)
    @ElementCollection
    @OrderColumn(name = "feed_order")
    private List<FeedId> ids;

    public FeedIds(List<FeedId> feedIds) {
        this.ids = feedIds;
    }
}
