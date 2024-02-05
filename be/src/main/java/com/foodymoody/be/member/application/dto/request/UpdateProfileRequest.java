package com.foodymoody.be.member.application.dto.request;

import lombok.Getter;

@Getter
public class UpdateProfileRequest {

    private String nickname;
    private String tasteMoodId;
    private String profileImageId;

}
