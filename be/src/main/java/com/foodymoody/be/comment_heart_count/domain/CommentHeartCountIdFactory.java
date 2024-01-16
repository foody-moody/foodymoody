package com.foodymoody.be.comment_heart_count.domain;

import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.common.util.ids.CommentHeartCountId;

public class CommentHeartCountIdFactory {

    private CommentHeartCountIdFactory() {
        throw new AssertionError();
    }

    public static CommentHeartCountId of(String value) {
        return new CommentHeartCountId(value);
    }

    public static CommentHeartCountId newId() {
        return new CommentHeartCountId(IdGenerator.generate());
    }
}
