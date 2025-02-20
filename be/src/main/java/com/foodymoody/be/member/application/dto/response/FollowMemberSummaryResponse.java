package com.foodymoody.be.member.application.dto.response;

import com.foodymoody.be.common.util.ids.MemberId;
import lombok.Getter;

@Getter
public class FollowMemberSummaryResponse {

    private MemberId id;
    private String nickname;
    private String profileImageUrl;
    private boolean following;
    private boolean followed;

    public FollowMemberSummaryResponse(MemberId id, String nickname, String profileImageUrl, boolean following,
                                       boolean followed) {
        this.id = id;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.following = following;
        this.followed = followed;
    }

    public static FollowMemberSummaryResponse of(MemberId id, String nickname, String profileImageUrl,
                                                 boolean isMyFollowing, boolean isMyFollower) {
        return new FollowMemberSummaryResponse(id, nickname, profileImageUrl, isMyFollowing, isMyFollower);
    }

}
