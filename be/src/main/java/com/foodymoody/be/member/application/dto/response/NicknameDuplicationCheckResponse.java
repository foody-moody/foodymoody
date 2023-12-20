package com.foodymoody.be.member.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NicknameDuplicationCheckResponse {

    @JsonProperty
    private boolean isDuplicate;

    public NicknameDuplicationCheckResponse(boolean isDuplicate) {
        this.isDuplicate = isDuplicate;
    }
}
