package com.foodymoody.be.common.util;

import com.foodymoody.be.common.util.ids.BaseId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IdResponse {

    private BaseId id;

    private IdResponse(BaseId id) {
        this.id = id;
    }

    public static IdResponse of(BaseId id) {
        return new IdResponse(id);
    }
}
