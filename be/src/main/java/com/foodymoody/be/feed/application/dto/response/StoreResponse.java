package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.StoreId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class StoreResponse {

    private StoreId id;
    private String name;

    public static StoreResponse from(StoreId id, String name) {
        return new StoreResponse(id, name);
    }

}
