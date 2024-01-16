package com.foodymoody.be.feed.domain.repository;

import com.foodymoody.be.common.util.ids.StoreMoodId;
import com.foodymoody.be.feed.domain.entity.StoreMood;
import java.util.List;
import java.util.Optional;

public interface StoreMoodRepository {

    Optional<StoreMood> fetchById(StoreMoodId id);

    List<StoreMood> fetchAll();

    List<StoreMood> findAllById(List<StoreMoodId> storeMoodIds);

}
