package com.foodymoody.be.member.domain;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.application.dto.response.NicknameDuplicationCheckResponse;
import com.foodymoody.be.member.application.dto.response.FollowMemberSummaryResponse;
import com.foodymoody.be.member.application.dto.request.MemberSignupRequest;
import com.foodymoody.be.member.application.dto.response.MemberSignupResponse;
import com.foodymoody.be.member.application.dto.FollowMemberSummary;
import org.springframework.data.domain.Slice;

public class MemberMapper {

    private MemberMapper() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static Member toEntity(MemberSignupRequest request, TasteMood tasteMood) {
        return Member.of(
                IdGenerator.generate(),
                request.getEmail(),
                request.getNickname(),
                request.getPassword(),
                request.getReconfirmPassword(),
                tasteMood.getId()
        );
    }

    public static MemberSignupResponse toSignupResponse(MemberId memberId) {
        return MemberSignupResponse.from(memberId);
    }

    public static NicknameDuplicationCheckResponse toNicknameDuplicationCheckResponse(boolean isDuplicate) {
        return new NicknameDuplicationCheckResponse(isDuplicate);
    }

    public static Slice<FollowMemberSummaryResponse> toFollowMemberSummaryResponse(Member currentMember, Slice<FollowMemberSummary> followInfoMembers) {
        return followInfoMembers.map(
                followMemberSummary -> FollowMemberSummaryResponse.of(
                            followMemberSummary.getId(),
                            followMemberSummary.getNickname(),
                            followMemberSummary.getProfileImageUrl(),
                            currentMember.isMyFollowing(followMemberSummary.getId()),
                            currentMember.isMyFollower(followMemberSummary.getId())));
    }

    public static Slice<FollowMemberSummaryResponse> toFollowMemberSummaryResponse(Slice<FollowMemberSummary> followInfoMembers) {
        return followInfoMembers.map(
                followMemberSummary -> FollowMemberSummaryResponse.of(
                        followMemberSummary.getId(),
                        followMemberSummary.getNickname(),
                        followMemberSummary.getProfileImageUrl(),
                        Boolean.FALSE,
                        Boolean.FALSE));
    }

}
