package com.foodymoody.be.member.util;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.controller.dto.NicknameDuplicationCheckResponse;
import com.foodymoody.be.member.controller.dto.MemberSignupRequest;
import com.foodymoody.be.member.controller.dto.MemberSignupResponse;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.domain.TasteMood;

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
        return MemberSignupResponse.from(memberId.getValue());
    }

    public static NicknameDuplicationCheckResponse toNicknameDuplicationCheckResponse(boolean isDuplicate) {
        return new NicknameDuplicationCheckResponse(isDuplicate);
    }
}
