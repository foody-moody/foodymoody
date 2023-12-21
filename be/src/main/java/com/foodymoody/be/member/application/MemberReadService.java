package com.foodymoody.be.member.application;

import com.foodymoody.be.common.exception.MemberNotFoundException;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.application.dto.response.FeedPreviewResponse;
import com.foodymoody.be.member.application.dto.response.MemberProfileResponse;
import com.foodymoody.be.member.application.dto.response.NicknameDuplicationCheckResponse;
import com.foodymoody.be.member.domain.MemberMapper;
import com.foodymoody.be.member.domain.MemberRepository;
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

    public MemberProfileResponse fetchProfile(MemberId currentMemberId, MemberId id) {
        return memberRepository.fetchMemberProfileResponseById(id, currentMemberId)
                .orElseThrow(MemberNotFoundException::new);
    }

    public Slice<FeedPreviewResponse> fetchFeedPreviews(MemberId id, Pageable pageable) {
        return memberRepository.fetchFeedPreviewResponsesById(id, pageable);
    }

    public NicknameDuplicationCheckResponse checkNicknameDuplication(String nickname) {
        boolean isDuplicate = memberRepository.existsByNickname(nickname);
        return MemberMapper.toNicknameDuplicationCheckResponse(isDuplicate);
    }

}
