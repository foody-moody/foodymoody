package com.foodymoody.be.common.util.ids;

import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class FeedReplyId extends BaseId {

    public FeedReplyId(String value) {
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
        if (!(obj instanceof FeedReplyId)) {
            return false;
        }
        FeedReplyId feedReplyId = (FeedReplyId) obj;
        return Objects.equals(getValue(), feedReplyId.getValue());
    }

}
