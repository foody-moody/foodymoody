package com.foodymoody.be.comment_heart.domain;

import com.foodymoody.be.common.util.IdGenerator;

public class CommentHeartIdFactory {

    private CommentHeartIdFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static CommentHeartId of(String value) {
        return new CommentHeartId(value);
    }

    public static CommentHeartId newId() {
        return new CommentHeartId(IdGenerator.generate());
    }
}
