package com.foodymoody.be.member.repository;

import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.domain.MemberId;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, MemberId> {

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    @Query(value = "SELECT new com.foodymoody.be.member.repository.MemberProfileResponse (m.id.id, i.url, m.nickname, m.email, t.id.id) "
            + "FROM Member m "
            + "LEFT JOIN FETCH Image i ON m.profileImageId = i.id "
            + "LEFT JOIN FETCH TasteMood t ON m.tasteMoodId = t.id "
            + "WHERE m.id.id = :id")
    Optional<MemberProfileResponse> fetchProfileById(@Param("id") String id);

    @Query("SELECT new com.foodymoody.be.member.repository.MemberFeedData (m.id.id, i.url, m.nickname, t.name) FROM Member m LEFT JOIN FETCH Image i ON m.profileImageId = i.id LEFT JOIN FETCH TasteMood t ON m.tasteMoodId = t.id WHERE m.id.id = :id")
    Optional<MemberFeedData> fetchFeedDataById(@Param("id") String id);

    @Query(value = "SELECT new com.foodymoody.be.member.repository.MemberProfileFeedPreviewResponse (f.id, i.url) FROM Feed f "
            + "INNER JOIN f.imageMenus.imageMenusList im "
            + "INNER JOIN FETCH Image i ON im.imageId = i.id "
            + "WHERE f.memberId = :id AND im.displayOrder = 0 "
            + "ORDER BY f.createdAt DESC")
    Slice<MemberProfileFeedPreviewResponse> fetchFeedPreviews(@Param("id") String id, Pageable pageable);
}