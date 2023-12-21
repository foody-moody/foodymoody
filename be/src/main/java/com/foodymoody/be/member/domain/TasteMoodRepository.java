package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.util.ids.TasteMoodId;
import java.util.List;
import java.util.Optional;

public interface TasteMoodRepository {

    Optional<TasteMood> findById(TasteMoodId id);

    List<TasteMood> findAll();
}
