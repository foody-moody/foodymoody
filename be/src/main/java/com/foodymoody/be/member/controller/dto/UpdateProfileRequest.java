package com.foodymoody.be.member.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UpdateProfileRequest {

    private String nickname;
    private String tasteMoodId;
    private String profileImageId;
}
