package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.StoreMoodId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FeedStoreMoodResponse {

    private StoreMoodId id;
    private String name;

    public static FeedStoreMoodResponse from(StoreMoodId id, String name) {
        return new FeedStoreMoodResponse(id, name);
    }

}
