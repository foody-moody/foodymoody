package com.foodymoody.be.member.util;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.member.controller.dto.MemberProfileFeedPreviewResponse;
import com.foodymoody.be.member.controller.dto.MemberProfileResponse;
import com.foodymoody.be.member.controller.dto.MemberSignupRequest;
import com.foodymoody.be.member.controller.dto.MemberSignupResponse;
import com.foodymoody.be.member.domain.Member;
import java.util.List;

public class MemberMapper {

    private MemberMapper() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static Member toEntity(MemberSignupRequest request) {
        return Member.of(
                IdGenerator.generate(),
                request.getEmail(),
                request.getNickname(),
                request.getPassword(),
                request.getReconfirmPassword(),
                request.getMood()
        );
    }

    public static MemberSignupResponse toSignupResponse(String savedMemberId) {
        return MemberSignupResponse.from(savedMemberId);
    }

    public static MemberProfileResponse toMemberProfileResponse(Member member, List<MemberProfileFeedPreviewResponse> feedPreviews) {
        return MemberProfileResponse.of(
                member.getId(),
                member.getProfileImageUrl(),
                member.getNickname(),
                member.getEmail(),
                member.getMood(),
                feedPreviews
        );
    }
}