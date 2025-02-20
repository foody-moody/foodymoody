package com.foodymoody.be.member.application.service;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.application.dto.FollowMemberSummary;
import com.foodymoody.be.member.application.dto.response.FollowMemberSummaryResponse;
import com.foodymoody.be.member.domain.FollowRepository;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.domain.MemberMapper;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowReadService {

    private final MemberReadService memberReadService;
    private final FollowRepository followRepository;

    public Slice<FollowMemberSummaryResponse> listFollowings(
            MemberId currentMemberId,
            MemberId id,
            Pageable pageable
    ) {
        Member member = memberReadService.findById(id);
        Slice<FollowMemberSummary> followings = followRepository.fetchMyFollowingSummariesByMember(member, pageable);
        return toFollowSummaryResponsesWithCurrentMember(currentMemberId, followings);
    }

    public Slice<FollowMemberSummaryResponse> listFollowers(
            MemberId currentMemberId,
            MemberId id,
            Pageable pageable
    ) {
        Member member = memberReadService.findById(id);
        Slice<FollowMemberSummary> followers = followRepository.fetchMyFollowerSummariesByMember(member, pageable);
        return toFollowSummaryResponsesWithCurrentMember(currentMemberId, followers);
    }

    public boolean isFollowing(MemberId currentMemberId, MemberId targetId) {
        Member currentMember = memberReadService.findById(currentMemberId);
        Member target = memberReadService.findById(targetId);
        return currentMember.isFollowing(target);
    }

    private Slice<FollowMemberSummaryResponse> toFollowSummaryResponsesWithCurrentMember(
            MemberId currentMemberId,
            Slice<FollowMemberSummary> followers
    ) {
        if (Objects.nonNull(currentMemberId)) {
            Member currentMember = memberReadService.findById(currentMemberId);
            return MemberMapper.toFollowMemberSummaryResponses(currentMember, followers);
        }
        return MemberMapper.toFollowMemberSummaryResponses(followers);
    }

}
