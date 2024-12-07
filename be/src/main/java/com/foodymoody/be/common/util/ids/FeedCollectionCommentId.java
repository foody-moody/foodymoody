package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
@EqualsAndHashCode(callSuper = true)
public class FeedCollectionCommentId extends BaseId {

    public FeedCollectionCommentId(String value) {
        super(value);
    }

}
