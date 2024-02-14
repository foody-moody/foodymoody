package com.foodymoody.be.member.application.service;

import com.foodymoody.be.common.exception.DuplicateNicknameException;
import com.foodymoody.be.common.exception.MemberNotFoundException;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.application.dto.FeedAuthorSummary;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionTitleResponse;
import com.foodymoody.be.member.application.dto.response.MyCollectionWithFeedInclusionStatusResponse;
import com.foodymoody.be.member.application.dto.response.MyFeedPreviewResponse;
import com.foodymoody.be.member.application.dto.response.MemberProfileResponse;
import com.foodymoody.be.member.application.dto.MyFeedCollectionWithFeedIdsSummary;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionsResponse;
import com.foodymoody.be.member.application.dto.response.NicknameDuplicationCheckResponse;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.domain.MemberMapper;
import com.foodymoody.be.member.domain.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberReadService {

    private final MemberRepository memberRepository;

    public Member findById(MemberId id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
    }

    public Member findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname).orElseThrow(MemberNotFoundException::new);
    }

    public MemberProfileResponse fetchProfile(MemberId currentMemberId, MemberId id) {
        return memberRepository.fetchMemberProfileResponseById(id, currentMemberId)
                .orElseThrow(MemberNotFoundException::new);
    }

    public Slice<MyFeedPreviewResponse> fetchFeedPreviews(MemberId id, Pageable pageable) {
        return memberRepository.fetchFeedPreviewResponsesById(id, pageable);
    }

    public MyFeedCollectionsResponse fetchMyCollections(MemberId id, MemberId currentMemberId, Pageable pageable) {
        return memberRepository.fetchMyCollectionSummaries(id, currentMemberId, pageable);

    }

    public FeedAuthorSummary fetchFeedAuthorSummaryById(MemberId id) {
        return memberRepository.fetchFeedAuthorSummaryById(id).orElseThrow(MemberNotFoundException::new);
    }

    public NicknameDuplicationCheckResponse checkNicknameDuplication(String nickname) {
        boolean isDuplicate = memberRepository.existsByNickname(nickname);
        return MemberMapper.toNicknameDuplicationCheckResponse(isDuplicate);
    }

    public void validateIdExists(MemberId id) {
        if (!memberRepository.existsById(id)) {
            throw new MemberNotFoundException();
        }
    }

    public void validateNicknameDuplication(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException();
        }
    }

    public List<MyFeedCollectionTitleResponse> fetchMyCollectionTitles(MemberId id) {
        return memberRepository.fetchMyCollectionTitles(id);
    }

    public List<MyCollectionWithFeedInclusionStatusResponse> fetchMyCollectionWithFeedInclusionStatus(MemberId currentMemberId, FeedId feedId) {
        List<MyFeedCollectionWithFeedIdsSummary> summaries = memberRepository.fetchMyCollectionWithFeedIds(currentMemberId);
        return MemberMapper.toMyFeedCollectionWithFeedInclusionStatusResponse(summaries, feedId);
    }
}