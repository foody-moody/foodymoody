package com.foodymoody.be.reply_heart.domain;

import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.common.util.ids.ReplyHeartId;

public class ReplyHeartIdFactory {

    private ReplyHeartIdFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static ReplyHeartId of(String value) {
        return new ReplyHeartId(value);
    }

    public static ReplyHeartId newId() {
        return new ReplyHeartId(IdGenerator.generate());
    }
}
