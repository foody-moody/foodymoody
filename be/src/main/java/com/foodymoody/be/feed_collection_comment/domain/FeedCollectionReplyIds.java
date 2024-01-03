package com.foodymoody.be.feed_collection_comment.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class FeedCollectionReplyIds {

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "feed_collection_reply_ids", joinColumns = @JoinColumn(name = "reply_id"))
    @OrderColumn(name = "comment_order", columnDefinition = "int default 0")
    private List<FeedCollectionReplyId> ids = new ArrayList<>();

    public List<FeedCollectionReplyId> getIds() {
        return ids;
    }

    public void add(FeedCollectionReplyId id) {
        ids.add(id);
    }
}
