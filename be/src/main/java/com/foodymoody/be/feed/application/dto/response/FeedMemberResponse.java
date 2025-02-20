package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.MemberId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FeedMemberResponse {

    private MemberId id;
    private String profileImageUrl;
    private String nickname;
    private FeedTasteMoodResponse tasteMood;

    @Builder
    private FeedMemberResponse(
            MemberId id,
            String profileImageUrl,
            String nickname,
            FeedTasteMoodResponse tasteMood
    ) {
        this.id = id;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        this.tasteMood = tasteMood;
    }

}
