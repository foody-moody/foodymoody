package com.foodymoody.be.member.service;

import com.foodymoody.be.common.exception.MemberNotFoundException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.repository.dto.MemberProfileFeedPreviewResponse;
import com.foodymoody.be.feed.service.FeedService;
import com.foodymoody.be.member.repository.MemberProfileResponse;
import com.foodymoody.be.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberProfileService {

    private final MemberRepository memberRepository;
    private final FeedService feedService;

    public Slice<MemberProfileFeedPreviewResponse> fetchProfileFeedPreviews(String id, Pageable pageable) {
        MemberId memberId = IdFactory.createMemberId(id);
        return feedService.findPreviewsByMemberId(memberId, pageable);
    }

    public MemberProfileResponse fetchProfile(String loginId, String id) {
        MemberId memberId = IdFactory.createMemberId(id);
        return memberRepository.fetchMemberProfileById(memberId, loginId)
                .orElseThrow(MemberNotFoundException::new);
    }

}
