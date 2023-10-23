package com.foodymoody.be.comment.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {

    @Id
    private String id;
    private String content;
    private long feedId;

    public Comment(String id, String content, long feedId) {
        this.id = id;
        this.content = content;
        this.feedId = feedId;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public long getFeedId() {
        return feedId;
    }
}
