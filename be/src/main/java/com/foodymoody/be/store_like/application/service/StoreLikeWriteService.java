package com.foodymoody.be.store_like.application.service;

import com.foodymoody.be.common.event.EventManager;
import com.foodymoody.be.common.exception.StoreLikeCancelFailedByNotLikedException;
import com.foodymoody.be.common.exception.StoreLikeRegisterFailedByAlreadyLikedException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.common.util.ids.StoreLikeId;
import com.foodymoody.be.store_like.domain.StoreLike;
import com.foodymoody.be.store_like.domain.StoreLikeRepository;
import com.foodymoody.be.store_like.util.StoreLikeMapper;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreLikeWriteService {

    private final StoreLikeRepository repository;

    public void register(StoreId storeId, MemberId memberId) {
        validateNotLiked(storeId, memberId);
        StoreLike created = create(storeId, memberId);
        EventManager.raise(StoreLikeMapper.toRegisteredEvent(created));
    }

    public void cancel(StoreId storeId, MemberId memberId) {
        validateLiked(storeId, memberId);
        repository.deleteByStoreIdAndMemberId(storeId, memberId);
        EventManager.raise(StoreLikeMapper.toCanceledEvent(storeId));
    }

    public StoreLike create(StoreId storeId, MemberId memberId) {
        StoreLikeId id = IdFactory.createStoreLikeId();
        LocalDateTime now = LocalDateTime.now();
        StoreLike storeLike = StoreLike.of(id, storeId, memberId, now);
        return repository.save(storeLike);
    }

    public void validateNotLiked(StoreId storeId, MemberId memberId) {
        if (isLiked(storeId, memberId)) {
            throw new StoreLikeRegisterFailedByAlreadyLikedException();
        }
    }

    public void validateLiked(StoreId storeId, MemberId memberId) {
        if (!isLiked(storeId, memberId)) {
            throw new StoreLikeCancelFailedByNotLikedException();
        }
    }

    public boolean isLiked(StoreId storeId, MemberId memberId) {
        return repository.findByStoreIdAndMemberId(storeId, memberId)
                .isPresent();
    }
}
