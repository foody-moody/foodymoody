package com.foodymoody.be.auth.application.dto;

import lombok.Getter;

@Getter
public class OAuthMemberDetails {

    private String email;
    private String nickname;
    private String profileImageUrl;

    public OAuthMemberDetails(String email, String nickname, String profileImageUrl) {
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public static OAuthMemberDetails of(String email, String nickname, String profileImageUrl) {
        return new OAuthMemberDetails(email, nickname, profileImageUrl);
    }
}
