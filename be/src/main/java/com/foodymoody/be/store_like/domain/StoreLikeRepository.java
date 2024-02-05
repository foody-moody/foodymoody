package com.foodymoody.be.store_like.domain;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreId;
import java.util.Optional;

public interface StoreLikeRepository {

    StoreLike save(StoreLike storeLike);

    void deleteByStoreIdAndMemberId(StoreId storeId, MemberId memberId);

    Optional<StoreLike> findByStoreIdAndMemberId(StoreId storeId, MemberId memberId);
}
