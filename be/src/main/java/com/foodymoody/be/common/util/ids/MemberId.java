package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@EqualsAndHashCode(callSuper = true)
public class MemberId extends BaseId {

    public MemberId(String value) {
        super(value);
    }

    /**
     * @deprecated equals를 사용해주세요
     * */
    @Deprecated(forRemoval = true)
    public boolean isSame(MemberId memberId) {
        return value.equals(memberId.value);
    }

}
