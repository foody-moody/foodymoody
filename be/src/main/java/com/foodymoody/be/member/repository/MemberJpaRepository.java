package com.foodymoody.be.member.repository;

import com.foodymoody.be.member.repository.dto.MemberCredential;
import com.foodymoody.be.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, String>, MemberRepository {

    @Query("select new com.foodymoody.be.member.repository.dto.MemberCredential(m.id, m.email, m.password) from Member m")
    Optional<MemberCredential> getCredentialByEmail(String email);

}
