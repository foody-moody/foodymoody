package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.MemberId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedTasteMoodResponse {

    private MemberId id;
    private String name;

    public FeedTasteMoodResponse(MemberId id, String name) {
        this.id = id;
        this.name = name;
    }

    public MemberId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
