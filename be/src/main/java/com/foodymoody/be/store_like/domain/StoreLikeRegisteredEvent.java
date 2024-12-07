package com.foodymoody.be.store_like.domain;

import com.foodymoody.be.common.event.Event;
import com.foodymoody.be.common.util.ids.StoreId;

public class StoreLikeRegisteredEvent implements Event {

    private final StoreId storeId;

    private StoreLikeRegisteredEvent(StoreId storeId) {
        this.storeId = storeId;
    }

    public static StoreLikeRegisteredEvent from(StoreId storeId) {
        return new StoreLikeRegisteredEvent(storeId);
    }

    public StoreId getStoreId() {
        return this.storeId;
    }

}
