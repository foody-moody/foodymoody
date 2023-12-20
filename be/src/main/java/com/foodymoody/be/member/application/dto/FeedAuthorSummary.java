package com.foodymoody.be.member.application.dto;

public class FeedAuthorSummary {

    private String id;
    private String profileImageUrl;
    private String nickname;
    private String moodName;

    public FeedAuthorSummary(String id, String profileImageUrl, String nickname, String moodName) {
        this.id = id;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        this.moodName = moodName;
    }

    public String getId() {
        return id;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMoodName() {
        return moodName;
    }
}
