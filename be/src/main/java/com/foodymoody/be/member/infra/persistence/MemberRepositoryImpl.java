package com.foodymoody.be.member.infra.persistence;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.application.dto.FeedAuthorSummary;
import com.foodymoody.be.member.application.dto.MyFeedCollectionWithFeedIdsSummary;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionTitleResponse;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionsResponse;
import com.foodymoody.be.member.application.dto.response.MyFeedPreviewResponse;
import com.foodymoody.be.member.application.dto.response.MemberProfileResponse;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.domain.MemberRepository;
import com.foodymoody.be.member.infra.persistence.jpa.MemberJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository jpaRepository;

    @Override
    public Optional<MemberProfileResponse> fetchMemberProfileResponseById(MemberId id, MemberId currentMemberId) {
        return jpaRepository.fetchMemberProfileResponseById(id, currentMemberId);
    }

    @Override
    public Optional<FeedAuthorSummary> fetchFeedAuthorSummaryById(MemberId id) {
        return jpaRepository.fetchFeedAuthorSummaryById(id);
    }

    @Override
    public Slice<MyFeedPreviewResponse> fetchFeedPreviewResponsesById(MemberId id, Pageable pageable) {
        return jpaRepository.fetchFeedPreviewResponsesById(id, pageable);
    }

    @Override
    public MyFeedCollectionsResponse fetchMyCollectionSummaries(MemberId id, MemberId currentMemberId, Pageable pageable) {
        return jpaRepository.fetchMyCollectionResponse(id, currentMemberId, pageable);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return jpaRepository.findByEmail(email);
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        return jpaRepository.findByNickname(nickname);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return jpaRepository.existsByNickname(nickname);
    }

    @Override
    public Member save(Member member) {
        return jpaRepository.save(member);
    }

    @Override
    public void delete(Member member) {
        jpaRepository.delete(member);
    }

    @Override
    public boolean existsById(MemberId id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public Optional<Member> findById(MemberId id) {
        return jpaRepository.findById(id);
    }

    @Override
    public long countMyCollectionsById(MemberId id) {
        return jpaRepository.countMyCollectionsById(id);
    }

    @Override
    public List<MyFeedCollectionTitleResponse> fetchMyCollectionTitles(MemberId id) {
        return jpaRepository.fetchMyCollectionTitles(id);
    }

    @Override
    public List<MyFeedCollectionWithFeedIdsSummary> fetchMyCollectionWithFeedIds(MemberId currentMemberId) {
        return jpaRepository.fetchMyFeedCollectionWithFeedIds(currentMemberId);
    }
}
