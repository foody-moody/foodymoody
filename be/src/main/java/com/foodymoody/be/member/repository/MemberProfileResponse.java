package com.foodymoody.be.member.repository;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberProfileResponse {

    @JsonProperty
    private String id;
    @JsonProperty
    private String profileImageUrl;
    @JsonProperty
    private String nickname;
    @JsonProperty
    private String email;
    @JsonProperty
    private String tasteMoodId;
    @JsonProperty
    private long followingCount;
    @JsonProperty
    private long followerCount;
    @JsonProperty
    private boolean isMyFollowing;
    @JsonProperty
    private boolean isMyFollower;
    @JsonProperty
    private long feedCount;

    public MemberProfileResponse(String id, String profileImageUrl, String nickname, String email, String tasteMoodId,
            long followingCount, long followerCount, boolean isMyFollowing, boolean isMyFollower, long feedCount) {
        this.id = id;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        this.email = email;
        this.tasteMoodId = tasteMoodId;
        this.followingCount = followingCount;
        this.followerCount = followerCount;
        this.isMyFollowing = isMyFollowing;
        this.isMyFollower = isMyFollower;
        this.feedCount = feedCount;
    }
}