package com.foodymoody.be.member.controller.dto;

import lombok.Getter;

@Getter
public class MemberSignupResponse {

    private String id;

    private MemberSignupResponse(String id) {
        this.id = id;
    }

    public static MemberSignupResponse from(String savedMemberId) {
        return new MemberSignupResponse(savedMemberId);
    }
}