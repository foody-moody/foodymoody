package com.foodymoody.be.member.application.dto.response;

import com.foodymoody.be.common.util.ids.TasteMoodId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TasteMoodResponse {

    private TasteMoodId id;
    private String name;

    public static TasteMoodResponse of(TasteMoodId id, String name) {
        return new TasteMoodResponse(id, name);
    }

}
