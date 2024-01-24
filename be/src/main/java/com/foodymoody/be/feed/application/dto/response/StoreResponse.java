package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.StoreId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StoreResponse {

    private StoreId id;
    private String name;

    public StoreResponse(StoreId id, String name) {
        this.id = id;
        this.name = name;
    }

}