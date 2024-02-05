package com.foodymoody.be.store.application.service.dto.response;

import com.foodymoody.be.common.util.ids.StoreId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreSearchResponse {

    private StoreId id;
    private String name;
    private String address;
    private String roadAddress;

}
