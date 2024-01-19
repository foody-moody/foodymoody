package com.foodymoody.be.feed.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.StoreMoodId;
import com.foodymoody.be.feed.application.dto.response.StoreMoodResponse;
import com.foodymoody.be.feed.domain.entity.StoreMood;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoreMoodJpaRepository extends JpaRepository<StoreMood, StoreMoodId> {

    @Query("SELECT NEW com.foodymoody.be.feed.application.dto.response.StoreMoodResponse(sm.id, sm.name) "
            + "FROM StoreMood sm")
    Optional<List<StoreMoodResponse>> fetchAll();

}
