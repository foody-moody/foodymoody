package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.MemberId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FeedTasteMoodResponse {

    private MemberId id;
    private String name;

    public FeedTasteMoodResponse(MemberId id, String name) {
        this.id = id;
        this.name = name;
    }

}
