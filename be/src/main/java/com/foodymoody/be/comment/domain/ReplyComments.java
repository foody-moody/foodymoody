package com.foodymoody.be.comment.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Embeddable
public class ReplyComments {

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    public void add(Comment replyComment) {
        commentList.add(replyComment);
    }

    public List<Comment> getCommentList() {
        return commentList;
    }
}
