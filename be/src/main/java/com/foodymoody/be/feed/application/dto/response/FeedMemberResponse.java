package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.MemberId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedMemberResponse {

    private MemberId id;
    private String imageUrl;
    private String nickname;
    private FeedTasteMoodResponse tasteMood;

    @Builder
    public FeedMemberResponse(MemberId id, String imageUrl, String nickname, FeedTasteMoodResponse tasteMood) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.nickname = nickname;
        this.tasteMood = tasteMood;
    }

    public MemberId getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public FeedTasteMoodResponse getTasteMood() {
        return tasteMood;
    }

}
