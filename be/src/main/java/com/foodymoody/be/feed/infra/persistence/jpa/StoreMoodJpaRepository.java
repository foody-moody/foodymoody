package com.foodymoody.be.feed.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.StoreMoodId;
import com.foodymoody.be.feed.domain.entity.StoreMood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreMoodJpaRepository extends JpaRepository<StoreMood, StoreMoodId> {
}
