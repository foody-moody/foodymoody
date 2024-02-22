package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.application.dto.FeedAuthorSummary;
import com.foodymoody.be.member.application.dto.MyFeedCollectionWithFeedIdsSummary;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionTitleResponse;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionsResponse;
import com.foodymoody.be.member.application.dto.response.MyFeedPreviewResponse;
import com.foodymoody.be.member.application.dto.response.MemberProfileResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface MemberRepository {

    Optional<MemberProfileResponse> fetchMemberProfileResponseById(MemberId id, MemberId currentMemberId);

    Optional<FeedAuthorSummary> fetchFeedAuthorSummaryById(MemberId id);

    Slice<MyFeedPreviewResponse> fetchFeedPreviewResponsesById(MemberId id, Pageable pageable);

    MyFeedCollectionsResponse fetchMyCollectionSummaries(MemberId id, MemberId currentMemberId, Pageable pageable);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    boolean existsByNickname(String nickname);

    Member save(Member member);

    void delete(Member member);

    boolean existsById(MemberId id);

    Optional<Member> findById(MemberId id);

    long countMyCollectionsById(MemberId id);

    List<MyFeedCollectionTitleResponse> fetchMyCollectionTitles(MemberId id);

    List<MyFeedCollectionWithFeedIdsSummary> fetchMyCollectionWithFeedIds(MemberId currentMemberId);

    boolean existsByEmail(String email);
}
