package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class MenuId extends BaseId {

    public MenuId(String value) {
        super(value);
    }

}
