package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.util.ids.StoreId;

public class StoreSummaryResponse {

    private StoreId id;
    private String name;

    public StoreSummaryResponse(StoreId id, String name) {
        this.id = id;
        this.name = name;
    }

    public StoreId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
