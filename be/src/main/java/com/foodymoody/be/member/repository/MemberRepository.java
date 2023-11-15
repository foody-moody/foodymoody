package com.foodymoody.be.member.repository;

import com.foodymoody.be.common.WrappedId;
import com.foodymoody.be.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, WrappedId> {

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    @Query("SELECT new com.foodymoody.be.member.repository.MemberProfileData (m.wrappedId.id, m.email, m.nickname, i.url, md.name) FROM Member m LEFT JOIN FETCH Image i ON m.profileImageId = i.id LEFT JOIN FETCH Mood md ON m.moodId = md.id WHERE m.wrappedId.id = :id")
    Optional<MemberProfileData> loadProfileDataById(String id);

    @Query("SELECT new com.foodymoody.be.member.repository.MemberFeedData (m.wrappedId.id, i.url, m.nickname, md.name) FROM Member m LEFT JOIN FETCH Image i ON m.profileImageId = i.id LEFT JOIN FETCH Mood md ON m.moodId = md.id WHERE m.wrappedId.id = :id")
    Optional<MemberFeedData> loadFeedDataById(String id);
}