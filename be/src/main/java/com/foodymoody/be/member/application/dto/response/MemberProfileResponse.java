package com.foodymoody.be.member.application.dto.response;

import com.foodymoody.be.common.util.ids.MemberId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberProfileResponse {

    private MemberId id;
    private MemberProfileImageResponse profileImage;
    private String nickname;
    private String email;
    private TasteMoodResponse tasteMood;
    private int followingCount;
    private int followerCount;
    private boolean following;
    private boolean followed;
    private long feedCount;

}