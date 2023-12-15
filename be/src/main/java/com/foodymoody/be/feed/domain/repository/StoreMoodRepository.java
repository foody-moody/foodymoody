package com.foodymoody.be.feed.domain.repository;

import com.foodymoody.be.common.util.ids.StoreMoodId;
import com.foodymoody.be.feed.domain.entity.StoreMood;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreMoodRepository extends JpaRepository<StoreMood, StoreMoodId> {

    List<StoreMood> findAllByIdIn(List<StoreMoodId> storeMoodIds);

}
