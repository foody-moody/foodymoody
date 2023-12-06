package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class CommentHeartId extends BaseId {

    public CommentHeartId(String value) {
        super(value);
    }
}
