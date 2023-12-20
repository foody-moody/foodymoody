package com.foodymoody.be.member.application.dto.response;

import lombok.Getter;

@Getter
public class TasteMoodResponse {

    private String id;
    private String name;

    public TasteMoodResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
