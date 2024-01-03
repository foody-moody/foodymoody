package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.StoreMoodId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedStoreMoodResponse {

    private StoreMoodId id;
    private String name;

    public FeedStoreMoodResponse(StoreMoodId id, String name) {
        this.id = id;
        this.name = name;
    }

    public StoreMoodId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
