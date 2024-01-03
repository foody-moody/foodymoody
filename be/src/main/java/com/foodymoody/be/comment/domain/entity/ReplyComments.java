package com.foodymoody.be.comment.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Embeddable
public class ReplyComments {

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Reply> commentList = new ArrayList<>();

    public void add(Reply reply) {
        commentList.add(reply);
    }

    public List<Reply> getCommentList() {
        return commentList;
    }
}
