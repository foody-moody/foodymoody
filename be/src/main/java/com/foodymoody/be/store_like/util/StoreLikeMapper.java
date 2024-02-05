package com.foodymoody.be.store_like.util;

import com.foodymoody.be.common.event.Event;
import com.foodymoody.be.common.util.Constants;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store_like.domain.StoreLike;
import com.foodymoody.be.store_like.domain.StoreLikeCanceledEvent;
import com.foodymoody.be.store_like.domain.StoreLikeRegisteredEvent;

public class StoreLikeMapper {

    private StoreLikeMapper() {
        throw new IllegalStateException(Constants.UTILITY_CLASS);
    }

    public static StoreLikeRegisteredEvent toRegisteredEvent(StoreLike created) {
        return StoreLikeRegisteredEvent.from(created.getStoreId());
    }

    public static StoreLikeCanceledEvent toCanceledEvent(StoreId storeId) {
        return StoreLikeCanceledEvent.from(storeId);
    }
}
