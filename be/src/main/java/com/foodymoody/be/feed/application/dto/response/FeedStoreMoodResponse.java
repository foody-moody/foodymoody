package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.StoreMoodId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FeedStoreMoodResponse {

    private StoreMoodId id;
    private String name;

    public FeedStoreMoodResponse(StoreMoodId id, String name) {
        this.id = id;
        this.name = name;
    }

}
