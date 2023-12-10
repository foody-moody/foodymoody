package com.foodymoody.be.member.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FollowInfoResponse {

    @JsonProperty
    private String id;
    @JsonProperty
    private String nickname;
    @JsonProperty
    private String profileImageUrl;
    @JsonProperty
    private boolean isMyFollowing;
    @JsonProperty
    private boolean isMyFollower;

    public FollowInfoResponse(String id, String nickname, String profileImageUrl, boolean isMyFollowing,
            boolean isMyFollower) {
        this.id = id;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.isMyFollowing = isMyFollowing;
        this.isMyFollower = isMyFollower;
    }

    public static FollowInfoResponse of (String id, String nickname, String profileImageUrl, boolean isMyFollowing, boolean isMyFollower) {
        return new FollowInfoResponse(id, nickname, profileImageUrl, isMyFollowing, isMyFollower);
    }

}
