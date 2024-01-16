package com.foodymoody.be.common.util.ids;

import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ReplyId extends BaseId {

    public ReplyId(String value) {
        super(value);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReplyId)) {
            return false;
        }
        ReplyId replyId = (ReplyId) obj;
        return Objects.equals(getValue(), replyId.getValue());
    }
}
