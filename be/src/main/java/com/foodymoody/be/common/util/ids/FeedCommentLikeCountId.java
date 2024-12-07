package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class FeedCommentLikeCountId extends BaseId {

    public FeedCommentLikeCountId(String value) {
        super(value);
    }

}
