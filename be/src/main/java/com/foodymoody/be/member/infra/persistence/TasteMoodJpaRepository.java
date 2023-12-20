package com.foodymoody.be.member.infra.persistence;

import com.foodymoody.be.common.util.ids.TasteMoodId;
import com.foodymoody.be.member.domain.TasteMood;
import com.foodymoody.be.member.domain.TasteMoodRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasteMoodJpaRepository extends TasteMoodRepository, JpaRepository<TasteMood, TasteMoodId> {

}
