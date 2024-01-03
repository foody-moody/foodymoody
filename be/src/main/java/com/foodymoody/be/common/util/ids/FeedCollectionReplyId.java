package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class FeedCollectionReplyId extends BaseId {

    public FeedCollectionReplyId(String value) {
        super(value);
    }
}
