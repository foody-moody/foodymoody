package com.foodymoody.be.member.util;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.member.controller.dto.MemberProfileFeedPreviewResponse;
import com.foodymoody.be.member.controller.dto.MemberProfileResponse;
import com.foodymoody.be.member.controller.dto.MemberSignupRequest;
import com.foodymoody.be.member.controller.dto.MemberSignupResponse;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.domain.MemberId;
import com.foodymoody.be.member.repository.MemberProfileData;
import java.util.List;

public class MemberMapper {

    private MemberMapper() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static Member toEntity(MemberSignupRequest request, String moodId) {
        return Member.of(
                IdGenerator.generate(),
                request.getEmail(),
                request.getNickname(),
                request.getPassword(),
                request.getReconfirmPassword(),
                moodId
        );
    }

    public static MemberSignupResponse toSignupResponse(MemberId memberId) {
        return MemberSignupResponse.from(memberId.getId());
    }

    public static MemberProfileResponse toMemberProfileResponse(MemberProfileData data, List<MemberProfileFeedPreviewResponse> feedPreviews) {
        return MemberProfileResponse.of(
                data.getId(),
                data.getProfileImageUrl(),
                data.getNickname(),
                data.getEmail(),
                data.getMoodName(),
                feedPreviews
        );
    }
}