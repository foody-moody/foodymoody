package com.foodymoody.be.feed.domain.repository;

import com.foodymoody.be.feed.domain.entity.StoreMood;
import com.foodymoody.be.feed.domain.entity.StoreMoodId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreMoodRepository extends JpaRepository<StoreMood, StoreMoodId> {

}
