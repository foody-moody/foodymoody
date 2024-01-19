package com.foodymoody.be.member.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.TasteMoodId;
import com.foodymoody.be.member.domain.TasteMood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasteMoodJpaRepository extends JpaRepository<TasteMood, TasteMoodId> {

}
