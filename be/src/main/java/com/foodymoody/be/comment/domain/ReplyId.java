package com.foodymoody.be.comment.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ReplyId implements Serializable {

    private static final long serialVersionUID = -5351955236373496259L;

    private String id;

    public ReplyId(String id) {
        this.id = id;
    }

    public static ReplyId from(String generate) {
        return new ReplyId(generate);
    }

    public String getId() {
        return id;
    }
}
