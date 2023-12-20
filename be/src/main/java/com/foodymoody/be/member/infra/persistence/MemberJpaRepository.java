package com.foodymoody.be.member.infra.persistence;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.application.dto.response.FeedPreviewResponse;
import com.foodymoody.be.member.application.dto.FeedAuthorSummary;
import com.foodymoody.be.member.application.dto.response.MemberProfileResponse;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.domain.MemberRepository;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberJpaRepository extends MemberRepository, JpaRepository<Member, MemberId> {

    // TODO 비즈니스 로직 어플리케이션 레이어로 이동
    @Override
    @Query("SELECT new com.foodymoody.be.member.application.dto.response.MemberProfileResponse (m.id, i.id, i.url, m.nickname, m.email, t.id, "
            + "COUNT(DISTINCT following), COUNT(DISTINCT follower),(COUNT(DISTINCT currentMemberFollowing) > 0), (COUNT(DISTINCT currentMemberFollower) > 0), COUNT(DISTINCT f)) "
                    + "FROM Member m "
                    + "LEFT JOIN Feed f ON m.id = f.memberId "
                    + "LEFT JOIN Image i ON m.profileImage.id = i.id "
                    + "LEFT JOIN TasteMood t ON m.tasteMoodId = t.id "
                    + "LEFT JOIN FETCH Follow following ON following.follower = m "
                    + "LEFT JOIN FETCH Follow follower ON follower.followed = m "
                    + "LEFT JOIN FETCH Follow currentMemberFollowing ON currentMemberFollowing.follower.id = :currentMemberId AND currentMemberFollowing.followed = m "
                    + "LEFT JOIN FETCH Follow currentMemberFollower ON currentMemberFollower.followed.id = :currentMemberId AND currentMemberFollower.follower = m "
                    + "WHERE m.id = :id "
                    + "GROUP BY m.id")
    Optional<MemberProfileResponse> fetchMemberProfileResponseById(MemberId id, MemberId currentMemberId);

    @Override
    @Query("SELECT new com.foodymoody.be.member.application.dto.FeedAuthorSummary (m.id, i.url, m.nickname, t.name) "
            + "FROM Member m "
            + "LEFT JOIN Image i ON m.profileImage.id = i.id "
            + "LEFT JOIN TasteMood t ON m.tasteMoodId = t.id "
            + "WHERE m.id = :id")
    Optional<FeedAuthorSummary> fetchFeedAuthorSummaryById(MemberId id);

    @Override
    @Query("SELECT new com.foodymoody.be.member.application.dto.response.FeedPreviewResponse (f.id, i.url) "
                    + "FROM Member m "
                    + "LEFT JOIN Feed f ON f.memberId = m.id "
                    + "INNER JOIN f.imageMenus.imageMenusList im "
                    + "INNER JOIN FETCH Image i ON im.imageId = i.id "
                    + "WHERE m.id = :id AND im.displayOrder = 0 "
                    + "ORDER BY f.createdAt DESC")
    Slice<FeedPreviewResponse> fetchFeedPreviewResponsesById(MemberId id, Pageable pageable);

    @Override
    Optional<Member> findByEmail(String email);

    @Override
    Optional<Member> findByNickname(String nickname);

    @Override
    boolean existsByEmail(String email);

    @Override
    boolean existsByNickname(String nickname);
}
