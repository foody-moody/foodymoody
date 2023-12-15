package com.foodymoody.be.feed.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodymoody.be.common.util.ids.StoreMoodId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedStoreMoodResponse {

    @JsonProperty
    private StoreMoodId id;
    @JsonProperty
    private String name;

    public FeedStoreMoodResponse(StoreMoodId id, String name) {
        this.id = id;
        this.name = name;
    }

}
