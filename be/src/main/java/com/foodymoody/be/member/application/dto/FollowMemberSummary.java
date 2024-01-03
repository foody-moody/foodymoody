package com.foodymoody.be.member.application.dto;

import com.foodymoody.be.common.util.ids.MemberId;
import lombok.Getter;

@Getter
public class FollowMemberSummary {

    private MemberId id;
    private String nickname;
    private String profileImageUrl;

    public FollowMemberSummary(MemberId id, String nickname, String profileImageUrl) {
        this.id = id;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

}
