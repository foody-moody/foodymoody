package com.foodymoody.be.member.repository;

public class MemberProfileData {

    private String id;
    private String email;
    private String nickname;
    private String profileImageUrl;
    private String moodName;

    public MemberProfileData(String id, String email, String nickname, String profileImageUrl, String moodName) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.moodName = moodName;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getMoodName() {
        return moodName;
    }
}
