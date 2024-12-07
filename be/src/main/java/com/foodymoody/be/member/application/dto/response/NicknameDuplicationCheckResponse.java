package com.foodymoody.be.member.application.dto.response;

import lombok.Getter;

@Getter
public class NicknameDuplicationCheckResponse {

    private boolean duplicate;

    public NicknameDuplicationCheckResponse(boolean duplicate) {
        this.duplicate = duplicate;
    }

}
