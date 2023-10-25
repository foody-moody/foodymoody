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
    private String feedId;

    public Comment(String id, String content, String feedId) {
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

    public String getFeedId() {
        return feedId;
    }

    public void edit(String content) {
        this.content = content;
    }
}
