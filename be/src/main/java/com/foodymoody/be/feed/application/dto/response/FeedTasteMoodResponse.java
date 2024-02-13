package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.MemberId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FeedTasteMoodResponse {

    private MemberId id;
    private String name;

    public static FeedTasteMoodResponse from(MemberId id, String name) {
        return new FeedTasteMoodResponse(id, name);
    }

}
