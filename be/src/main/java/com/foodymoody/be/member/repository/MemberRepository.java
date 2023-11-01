package com.foodymoody.be.member.repository;

import com.foodymoody.be.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}