package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class StoreMoodId extends BaseId {

    public StoreMoodId(String value) {
        super(value);
    }

}
