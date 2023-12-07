package com.foodymoody.be.collection.domain;

import com.foodymoody.be.common.util.ids.CommentId;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.OrderColumn;

@Embeddable
public class CommentIds {

    @ElementCollection
    @OrderColumn(name = "comment_order")
    private List<CommentId> ids;


    public CommentIds() {
        this.ids = new ArrayList<>();
    }
}
