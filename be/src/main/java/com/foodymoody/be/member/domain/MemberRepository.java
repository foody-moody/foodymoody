package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.application.dto.FeedAuthorSummary;
import com.foodymoody.be.member.application.dto.response.MyCollectionTitleResponse;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionsResponse;
import com.foodymoody.be.member.application.dto.response.FeedPreviewResponse;
import com.foodymoody.be.member.application.dto.response.MemberProfileResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface MemberRepository {

    Optional<MemberProfileResponse> fetchMemberProfileResponseById(MemberId id, MemberId currentMemberId);

    Optional<FeedAuthorSummary> fetchFeedAuthorSummaryById(MemberId id);

    Slice<FeedPreviewResponse> fetchFeedPreviewResponsesById(MemberId id, Pageable pageable);

    MyFeedCollectionsResponse fetchMyCollectionSummaries(MemberId id, MemberId currentMemberId, Pageable pageable);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    Member save(Member member);

    void delete(Member member);

    boolean existsById(MemberId id);

    Optional<Member> findById(MemberId id);

    long countMyCollectionsById(MemberId id);

    List<MyCollectionTitleResponse> fetchMyCollectionTitles(MemberId id);
}
