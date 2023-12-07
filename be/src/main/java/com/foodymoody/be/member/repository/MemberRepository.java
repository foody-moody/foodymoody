package com.foodymoody.be.member.repository;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.domain.Member;
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

    @Query(value =
            "SELECT new com.foodymoody.be.member.repository.MemberProfileResponse (m.id.value, i.url, m.nickname, m.email, t.id.value) "
                    + "FROM Member m "
                    + "LEFT JOIN FETCH Image i ON m.profileImageId = i.id "
                    + "LEFT JOIN FETCH TasteMood t ON m.tasteMoodId = t.id "
                    + "WHERE m.id = :id")
    Optional<MemberProfileResponse> fetchProfileById(@Param("id") MemberId id);

    @Query("SELECT new com.foodymoody.be.member.repository.MemberFeedData (m.id.value, i.url, m.nickname, t.name) "
            + "FROM Member m "
            + "LEFT JOIN FETCH Image i ON m.profileImageId = i.id "
            + "LEFT JOIN FETCH TasteMood t ON m.tasteMoodId = t.id "
            + "WHERE m.id = :id")
    Optional<MemberFeedData> fetchFeedDataById(@Param("id") MemberId id);

}
