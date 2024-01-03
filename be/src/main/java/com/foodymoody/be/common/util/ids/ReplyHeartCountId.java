package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class ReplyHeartCountId extends BaseId {

    public ReplyHeartCountId(String value) {
        super(value);
    }
}
