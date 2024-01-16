package com.foodymoody.be.feed.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.StoreMoodId;
import com.foodymoody.be.feed.domain.entity.StoreMood;
import com.foodymoody.be.feed.domain.repository.StoreMoodRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class StoreMoodRepositoryImpl implements StoreMoodRepository {

    private final StoreMoodJpaRepository storeMoodJpaRepository;

    @Override
    public Optional<StoreMood> fetchById(StoreMoodId id) {
        return storeMoodJpaRepository.findById(id);
    }

    @Override
    public List<StoreMood> fetchAll() {
        return storeMoodJpaRepository.findAll();
    }

    @Override
    public List<StoreMood> findAllById(List<StoreMoodId> storeMoodIds) {
        return storeMoodJpaRepository.findAllById(storeMoodIds);
    }

}
