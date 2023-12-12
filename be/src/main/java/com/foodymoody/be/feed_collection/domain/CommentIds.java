package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.util.ids.CommentId;
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
    @CollectionTable(name = "feed_collection_comment_ids", joinColumns = @JoinColumn(name = "comment_id"))
    @OrderColumn(name = "comment_order", columnDefinition = "int default 0")
    private List<CommentId> ids = new ArrayList<>();

}
