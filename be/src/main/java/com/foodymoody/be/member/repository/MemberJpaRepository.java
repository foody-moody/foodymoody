package com.foodymoody.be.member.repository;

import com.foodymoody.be.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, String>, MemberRepository {
}
