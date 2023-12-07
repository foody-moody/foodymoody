package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class CommentHeartCountId extends BaseId {

    public CommentHeartCountId(String value) {
        super(value);
    }
}
