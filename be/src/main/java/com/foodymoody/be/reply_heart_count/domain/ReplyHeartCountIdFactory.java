package com.foodymoody.be.reply_heart_count.domain;

import com.foodymoody.be.common.util.IdGenerator;

public class ReplyHeartCountIdFactory {

    private ReplyHeartCountIdFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static ReplyHeartCountId of(String value) {
        return new ReplyHeartCountId(value);
    }

    public static ReplyHeartCountId newId() {
        return new ReplyHeartCountId(IdGenerator.generate());
    }
}
