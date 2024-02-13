package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.FeedId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedRegisterResponse {

    private FeedId id;

    public static FeedRegisterResponse of(FeedId id) {
        return new FeedRegisterResponse(id);
    }

}
