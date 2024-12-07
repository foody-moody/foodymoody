package com.foodymoody.be.member.infra.persistence.jpa;

import com.foodymoody.be.common.auth.SupportedAuthProvider;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.application.dto.FeedAuthorSummary;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionTitleResponse;
import com.foodymoody.be.member.application.dto.response.MyFeedPreviewResponse;
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

    boolean existsByEmailAndAuthProvider(String email, SupportedAuthProvider authProvider);

    boolean existsByNickname(String nickname);

    @Query("SELECT count(*) "
            + "FROM Member m "
            + "LEFT JOIN FETCH FeedCollection fc ON fc.authorId = m.id "
            + "WHERE m.id = :id")
    long countMyCollectionsById(MemberId id);

    @Query("SELECT new com.foodymoody.be.member.application.dto.response.MyFeedCollectionTitleResponse (fc.id, fc.title, fc.isPrivate) "
            + "FROM Member m "
            + "LEFT JOIN FETCH FeedCollection fc ON fc.authorId = m.id "
            + "WHERE m.id = :id AND fc.isDeleted = false "
            + "ORDER BY fc.createdAt DESC")
    List<MyFeedCollectionTitleResponse> fetchMyCollectionTitles(MemberId id);

    boolean existsByEmail(String email);

    @Query("SELECT count(f) "
            + "FROM Member m "
            + "LEFT JOIN Feed f ON f.memberId = m.id "
            + "WHERE m.id = :id")
    long countMyFeedById(MemberId id);

}
