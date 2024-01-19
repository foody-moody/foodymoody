package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class FeedReplyLikeId extends BaseId {

    public FeedReplyLikeId(String value) {
        super(value);
    }
}
