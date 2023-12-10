package com.foodymoody.be.member.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateProfileRequest {

    private String profileImageId;
    private String tasteMoodId;

    public UpdateProfileRequest(String profileImageId, String tasteMoodId) {
        this.profileImageId = profileImageId;
        this.tasteMoodId = tasteMoodId;
    }
}
