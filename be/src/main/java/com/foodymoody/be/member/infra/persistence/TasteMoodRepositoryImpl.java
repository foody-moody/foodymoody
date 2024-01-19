package com.foodymoody.be.member.infra.persistence;

import com.foodymoody.be.common.util.ids.TasteMoodId;
import com.foodymoody.be.member.domain.TasteMood;
import com.foodymoody.be.member.domain.TasteMoodRepository;
import com.foodymoody.be.member.infra.persistence.jpa.TasteMoodJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TasteMoodRepositoryImpl implements TasteMoodRepository {

    private final TasteMoodJpaRepository jpaRepository;

    @Override
    public Optional<TasteMood> findById(TasteMoodId id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<TasteMood> findAll() {
        return jpaRepository.findAll();
    }
}
