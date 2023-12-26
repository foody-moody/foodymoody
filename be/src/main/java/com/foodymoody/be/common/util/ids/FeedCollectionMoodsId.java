package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class FeedCollectionMoodsId extends BaseId {

    public FeedCollectionMoodsId(String value) {
        super(value);
    }
}
