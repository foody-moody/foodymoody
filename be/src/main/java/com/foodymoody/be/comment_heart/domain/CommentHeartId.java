package com.foodymoody.be.comment_heart.domain;

import com.foodymoody.be.common.util.BaseId;
import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class CommentHeartId extends BaseId {

    CommentHeartId(String value) {
        super(value);
    }
}
