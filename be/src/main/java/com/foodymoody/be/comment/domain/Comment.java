package com.foodymoody.be.comment.domain;

public class Comment {

    private long id;
    private String content;
    private long feedId;

    public Comment(long id, String content, long feedId) {
        this.id = id;
        this.content = content;
        this.feedId = feedId;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public long getFeedId() {
        return feedId;
    }
}
