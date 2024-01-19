package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.FeedId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedRegisterResponse {

    private FeedId id;

    public FeedRegisterResponse(FeedId id) {
        this.id = id;
    }

}
