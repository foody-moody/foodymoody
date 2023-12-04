package com.foodymoody.be.reply_heart.domain;

import com.foodymoody.be.common.util.BaseId;
import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class ReplyHeartId extends BaseId {

    ReplyHeartId(String value) {
        super(value);
    }
}
