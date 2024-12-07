package com.foodymoody.be.store_like_count.domain;

import com.foodymoody.be.common.util.ids.StoreId;
import java.util.Optional;

public interface StoreLikeCountRepository {

    Optional<StoreLikeCount> findByStoreId(StoreId storeId);

    StoreLikeCount save(StoreLikeCount storeLikeCount);

}
