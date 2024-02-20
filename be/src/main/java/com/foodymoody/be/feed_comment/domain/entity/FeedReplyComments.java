package com.foodymoody.be.feed_comment.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Embeddable
public class FeedReplyComments {

    @OneToMany(cascade = {CascadeType.PERSIST}, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FeedReply> commentList = new ArrayList<>();

    public void add(FeedReply feedReply) {
        commentList.add(feedReply);
    }

    public List<FeedReply> getCommentList() {
        return commentList;
    }

    public void delete(FeedReply feedReply) {
        commentList.remove(feedReply);
    }

    public boolean isEmpty() {
        return commentList.isEmpty();
    }
}
