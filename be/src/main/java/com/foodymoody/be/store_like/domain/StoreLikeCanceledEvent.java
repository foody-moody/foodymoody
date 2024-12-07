package com.foodymoody.be.store_like.domain;

import com.foodymoody.be.common.event.Event;
import com.foodymoody.be.common.util.ids.StoreId;

public class StoreLikeCanceledEvent implements Event {

    private StoreId storeId;

    private StoreLikeCanceledEvent(StoreId storeId) {
        this.storeId = storeId;
    }

    public static StoreLikeCanceledEvent from(StoreId storeId) {
        return new StoreLikeCanceledEvent(storeId);
    }

    public StoreId getStoreId() {
        return this.storeId;
    }

}
