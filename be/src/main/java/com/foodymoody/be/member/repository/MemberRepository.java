package com.foodymoody.be.member.repository;

import com.foodymoody.be.member.repository.dto.MemberCredential;
import com.foodymoody.be.member.domain.Member;
import java.util.Optional;

public interface MemberRepository{

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    Member findByEmail(String email);

    Member save(Member entity);

    Optional<Member> findById(String id);

    Optional<MemberCredential> getCredentialByEmail(String email);
}