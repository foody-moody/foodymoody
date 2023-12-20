package com.foodymoody.be.member.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberProfileResponse {

    private String id;
    private String profileImageId;
    private String profileImageUrl;
    private String nickname;
    private String email;
    private String tasteMoodId;
    private long followingCount;
    private long followerCount;
    private boolean following;
    private boolean followed;
    private long feedCount;

}