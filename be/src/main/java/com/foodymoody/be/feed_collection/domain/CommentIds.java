package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;

@Embeddable
public class CommentIds {

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "feed_collection_comment_ids", joinColumns = @JoinColumn(name = "feed_collection_id"))
    @OrderColumn(name = "comment_order", columnDefinition = "int default 0")
    private List<FeedCollectionCommentId> ids = new ArrayList<>();

    public List<FeedCollectionCommentId> getIds() {
        return ids;
    }

    public void add(FeedCollectionCommentId collectionCommentId) {
        ids.add(collectionCommentId);
    }

    public void remove(FeedCollectionCommentId collectionCommentId) {
        ids.remove(collectionCommentId);
    }
}
