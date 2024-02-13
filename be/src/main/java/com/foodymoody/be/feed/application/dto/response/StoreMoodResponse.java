package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.StoreMoodId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StoreMoodResponse {

    private StoreMoodId id;
    private String name;

    /**
     * StoreMoodJpaRepository에서 사용 중
     */
    public StoreMoodResponse(StoreMoodId id, String name) {
        this.id = id;
        this.name = name;
    }

}
