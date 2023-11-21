package com.foodymoody.be.member.repository;

import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.domain.MemberId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, MemberId> {

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    @Query("SELECT new com.foodymoody.be.member.repository.MemberProfileData (m.id.id, m.email, m.nickname, i.url, md.name) FROM Member m LEFT JOIN FETCH Image i ON m.profileImageId = i.id LEFT JOIN FETCH Mood md ON m.moodId = md.id WHERE m.id.id = :id")
    Optional<MemberProfileData> fetchProfileDataById(@Param("id") String id);

    @Query("SELECT new com.foodymoody.be.member.repository.MemberFeedData (m.id.id, i.url, m.nickname, md.name) FROM Member m LEFT JOIN FETCH Image i ON m.profileImageId = i.id LEFT JOIN FETCH Mood md ON m.moodId = md.id WHERE m.id.id = :id")
    Optional<MemberFeedData> fetchFeedDataById(@Param("id") String id);
}
