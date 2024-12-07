package com.foodymoody.be.store_like_count.domain;

import com.foodymoody.be.common.exception.ErrorMessage;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Count {

    @EqualsAndHashCode.Include
    private long count;

    public Count(long count) {
        validate(count);
        this.count = count;
    }

    public static Count create() {
        return new Count();
    }

    public static Count from(long count) {
        return new Count(count);
    }

    public long getCount() {
        return count;
    }

    private void validate(long count) {
        if (count < 0) {
            throw new IllegalArgumentException(ErrorMessage.COUNT_OUT_OF_BOUNDS.getMessage());
        }
    }

}
