package com.foodymoody.be.member.application;

import com.foodymoody.be.common.exception.MemberNotFoundException;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.domain.repository.dto.MemberProfileFeedPreviewResponse;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.member.application.dto.response.MemberProfileResponse;
import com.foodymoody.be.member.infra.persistence.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberProfileService {

    private final MemberJpaRepository memberRepository;
    private final FeedReadService feedReadService;

    public Slice<MemberProfileFeedPreviewResponse> fetchProfileFeedPreviews(MemberId id, Pageable pageable) {
        return feedReadService.fetchPreviewsByMemberId(id, pageable);
    }

    public MemberProfileResponse fetchProfile(MemberId currentMemberId, MemberId id) {
        return memberRepository.fetchMemberProfileById(id, currentMemberId)
                .orElseThrow(MemberNotFoundException::new);
    }

}
