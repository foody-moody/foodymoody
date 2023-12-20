package com.foodymoody.be.member.application.dto.response;

import com.foodymoody.be.common.util.ids.TasteMoodId;
import lombok.Getter;

@Getter
public class TasteMoodResponse {

    private TasteMoodId id;
    private String name;

    public TasteMoodResponse(TasteMoodId id, String name) {
        this.id = id;
        this.name = name;
    }
}
