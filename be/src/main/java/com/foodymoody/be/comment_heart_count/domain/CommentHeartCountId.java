package com.foodymoody.be.comment_heart_count.domain;

import com.foodymoody.be.common.util.BaseId;
import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class CommentHeartCountId extends BaseId {

    public CommentHeartCountId(String value) {
        super(value);
    }
}
