package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@EqualsAndHashCode(callSuper = false)
public class MemberId extends BaseId {

    public MemberId(String value) {
        super(value);
    }

    public boolean isSame(MemberId memberId) {
        return value.equals(memberId.value);
    }

    @Override
    @EqualsAndHashCode.Include
    public String getValue() {
        return super.getValue();
    }
}
