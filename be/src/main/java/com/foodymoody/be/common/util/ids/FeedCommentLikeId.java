package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class FeedCommentLikeId extends BaseId {

    public FeedCommentLikeId(String value) {
        super(value);
    }
}
