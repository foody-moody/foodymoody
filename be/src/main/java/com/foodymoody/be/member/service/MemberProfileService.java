package com.foodymoody.be.member.service;

import com.foodymoody.be.common.exception.MemberNotFoundException;
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
        return feedService.findPreviewsByMemberId(id, pageable);
    }

    public MemberProfileResponse fetchProfile(String id) {
        return memberRepository.fetchProfileById(id)
                .orElseThrow(MemberNotFoundException::new);
    }

}
