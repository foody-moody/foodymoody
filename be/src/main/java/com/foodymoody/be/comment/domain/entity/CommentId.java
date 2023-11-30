package com.foodymoody.be.comment.domain.entity;

import com.foodymoody.be.common.util.BaseId;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class CommentId extends BaseId {

    public CommentId(String value) {
        super(value);
    }
}
