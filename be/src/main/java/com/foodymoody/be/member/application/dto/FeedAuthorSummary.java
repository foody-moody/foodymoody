package com.foodymoody.be.member.application.dto;

import com.foodymoody.be.common.util.ids.MemberId;
import lombok.Getter;

@Getter
public class FeedAuthorSummary {

    private MemberId id;
    private String profileImageUrl;
    private String nickname;
    private String moodName;

    public FeedAuthorSummary(MemberId id, String profileImageUrl, String nickname, String moodName) {
        this.id = id;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        this.moodName = moodName;
    }

}
