package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class FeedCollectionCommentId extends BaseId {

    public FeedCollectionCommentId(String value) {
        super(value);
    }
}
