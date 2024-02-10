package com.foodymoody.be.member.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.application.dto.response.MyCollectionTitleResponse;
import com.foodymoody.be.member.application.dto.response.MyFeedPreviewResponse;
import com.foodymoody.be.member.application.dto.FeedAuthorSummary;
import com.foodymoody.be.member.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberJpaRepository extends JpaRepository<Member, MemberId>, MemberQueryDslRepository {

    @Query("SELECT new com.foodymoody.be.member.application.dto.FeedAuthorSummary (m.id, i.url, m.nickname, t.name) "
            + "FROM Member m "
            + "LEFT JOIN Image i ON m.profileImage.id = i.id "
            + "LEFT JOIN FETCH TasteMood t ON m.tasteMood = t "
            + "WHERE m.id = :id")
    Optional<FeedAuthorSummary> fetchFeedAuthorSummaryById(MemberId id);

    @Query("SELECT new com.foodymoody.be.member.application.dto.response.MyFeedPreviewResponse (f.id, i.url) "
                    + "FROM Member m "
                    + "LEFT JOIN Feed f ON f.memberId = m.id "
                    + "INNER JOIN f.imageMenus.imageMenusList im "
                    + "INNER JOIN FETCH Image i ON im.imageId = i.id "
                    + "WHERE m.id = :id AND im.displayOrder = 0 "
                    + "ORDER BY f.createdAt DESC")
    Slice<MyFeedPreviewResponse> fetchFeedPreviewResponsesById(MemberId id, Pageable pageable);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    @Query("SELECT count(*) "
            + "FROM Member m "
            + "LEFT JOIN FETCH FeedCollection fc ON fc.authorId = m.id "
            + "WHERE m.id = :id")
    long countMyCollectionsById(MemberId id);

    @Query("SELECT new com.foodymoody.be.member.application.dto.response.MyCollectionTitleResponse (fc.id, fc.title) "
            + "FROM Member m "
            + "LEFT JOIN FETCH FeedCollection fc ON fc.authorId = m.id "
            + "WHERE m.id = :id "
            + "ORDER BY fc.createdAt DESC")
    List<MyCollectionTitleResponse> fetchMyCollectionTitles(MemberId id);

}
