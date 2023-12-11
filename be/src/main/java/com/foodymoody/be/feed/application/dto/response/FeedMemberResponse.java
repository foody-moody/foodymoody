package com.foodymoody.be.feed.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedMemberResponse {

    @JsonProperty
    private String id;
    @JsonProperty
    private String imageUrl;
    @JsonProperty
    private String nickname;
    @JsonProperty
    private FeedTasteMoodResponse tasteMood;

    @Builder
    public FeedMemberResponse(String id, String imageUrl, String nickname, FeedTasteMoodResponse tasteMood) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.nickname = nickname;
        this.tasteMood = tasteMood;
    }

}
