package com.foodymoody.be.member.application.dto.response;

import com.foodymoody.be.common.util.ids.MemberId;
import lombok.Getter;

@Getter
public class MemberSignupResponse {

    private MemberId id;

    private MemberSignupResponse(MemberId id) {
        this.id = id;
    }

    public static MemberSignupResponse from(MemberId savedMemberId) {
        return new MemberSignupResponse(savedMemberId);
    }
}