package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.StoreMoodId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StoreMoodResponse {

    private StoreMoodId id;
    private String name;

    public StoreMoodResponse(StoreMoodId id, String name) {
        this.id = id;
        this.name = name;
    }

}
