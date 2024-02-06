package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class FeedReplyLikeCountId extends BaseId {

    public FeedReplyLikeCountId(String value) {
        super(value);
    }
}
