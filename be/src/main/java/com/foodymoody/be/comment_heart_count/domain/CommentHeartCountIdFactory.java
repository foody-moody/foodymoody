package com.foodymoody.be.comment_heart_count.domain;

import com.foodymoody.be.common.util.IdGenerator;

public class CommentHeartCountIdFactory {

    private CommentHeartCountIdFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static CommentHeartCountId of(String value) {
        return new CommentHeartCountId(value);
    }

    public static CommentHeartCountId newId() {
        return new CommentHeartCountId(IdGenerator.generate());
    }
}
