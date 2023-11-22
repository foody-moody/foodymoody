package com.foodymoody.be.feed.repository;

import com.foodymoody.be.feed.domain.StoreMood;
import com.foodymoody.be.feed.domain.StoreMoodId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreMoodRepository extends JpaRepository<StoreMood, StoreMoodId> {

}
