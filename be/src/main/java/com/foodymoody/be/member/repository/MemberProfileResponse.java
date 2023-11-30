package com.foodymoody.be.member.repository;

import lombok.Getter;

@Getter
public class MemberProfileResponse {

    private String id;
    private String profileImageUrl;
    private String nickname;
    private String email;
    private String tasteMood;

    public MemberProfileResponse(String id, String profileImageUrl, String nickname, String email, String mood) {
        this.id = id;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        this.email = email;
        this.tasteMood = mood;
    }

}