package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class FeedCollectionId extends BaseId {

    public FeedCollectionId(String value) {
        super(value);
    }
}
