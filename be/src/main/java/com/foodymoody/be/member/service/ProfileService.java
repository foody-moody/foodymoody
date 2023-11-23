package com.foodymoody.be.member.service;

import com.foodymoody.be.common.exception.MemberNotFoundException;
import com.foodymoody.be.feed.repository.MemberProfileFeedPreviewResponse;
import com.foodymoody.be.feed.service.FeedService;
import com.foodymoody.be.member.controller.dto.MemberProfileResponse;
import com.foodymoody.be.member.repository.MemberProfileData;
import com.foodymoody.be.member.repository.MemberRepository;
import com.foodymoody.be.member.util.MemberMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {

    private final MemberRepository memberRepository;
    private final FeedService feedService;

    public MemberProfileResponse fetchProfile(String id) {
        MemberProfileData data = fetchProfileData(id);
        List<MemberProfileFeedPreviewResponse> feedPreviews = feedService.readAllPreviewsByMemberId(id);
        return MemberMapper.toMemberProfileResponse(data, feedPreviews);
    }

    private MemberProfileData fetchProfileData(String id) {
        return memberRepository.fetchProfileDataById(id)
                .orElseThrow(MemberNotFoundException::new);
    }
}
