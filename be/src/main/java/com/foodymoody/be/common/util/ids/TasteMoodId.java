package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class TasteMoodId extends BaseId {

    public TasteMoodId(String value) {
        super(value);
    }
}
