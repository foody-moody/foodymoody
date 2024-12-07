package com.foodymoody.be.member.infra.persistence;

import com.foodymoody.be.member.application.dto.FollowMemberSummary;
import com.foodymoody.be.member.domain.FollowRepository;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.infra.persistence.jpa.FollowJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepository {

    private final FollowJpaRepository jpaRepository;

    @Override
    public Slice<FollowMemberSummary> fetchMyFollowingSummariesByMember(Member member, Pageable pageable) {
        return jpaRepository.fetchMyFollowingSummariesByMember(member, pageable);
    }

    @Override
    public Slice<FollowMemberSummary> fetchMyFollowerSummariesByMember(Member member, Pageable pageable) {
        return jpaRepository.fetchMyFollowerSummariesByMember(member, pageable);
    }

}
