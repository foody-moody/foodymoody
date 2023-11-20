package com.foodymoody.be.comment.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class CommentId implements Serializable {

    private static final long serialVersionUID = -5351955236373496259L;

    private String id;

    public CommentId(String id) {
        this.id = id;
    }

    public static CommentId from(String generate) {
        return new CommentId(generate);
    }

    public String getId() {
        return id;
    }
}
