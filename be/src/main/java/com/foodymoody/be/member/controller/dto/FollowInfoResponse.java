package com.foodymoody.be.member.controller.dto;

import lombok.Getter;

@Getter
public class FollowInfoResponse {

    private String id;
    private String nickname;
    private String profileImageUrl;
    private boolean following;
    private boolean followed;

    public FollowInfoResponse(String id, String nickname, String profileImageUrl, boolean following,
            boolean followed) {
        this.id = id;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.following = following;
        this.followed = followed;
    }

    public static FollowInfoResponse of (String id, String nickname, String profileImageUrl, boolean isMyFollowing, boolean isMyFollower) {
        return new FollowInfoResponse(id, nickname, profileImageUrl, isMyFollowing, isMyFollower);
    }

}
