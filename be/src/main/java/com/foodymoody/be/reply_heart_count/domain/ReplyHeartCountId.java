package com.foodymoody.be.reply_heart_count.domain;

import com.foodymoody.be.common.util.BaseId;
import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class ReplyHeartCountId extends BaseId {

    public ReplyHeartCountId(String value) {
        super(value);
    }
}
