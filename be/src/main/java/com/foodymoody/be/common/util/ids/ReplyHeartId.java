package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class ReplyHeartId extends BaseId {

    public ReplyHeartId(String value) {
        super(value);
    }
}
