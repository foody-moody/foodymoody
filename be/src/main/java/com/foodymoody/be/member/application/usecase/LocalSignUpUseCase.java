package com.foodymoody.be.member.application.usecase;

import com.foodymoody.be.common.auth.SupportedAuthProvider;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.image.application.service.ImageService;
import com.foodymoody.be.member.application.dto.request.SignupRequest;
import com.foodymoody.be.member.application.dto.response.MemberSignupResponse;
import com.foodymoody.be.member.application.service.MemberWriteService;
import com.foodymoody.be.member.application.service.TasteMoodReadService;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.domain.MemberMapper;
import com.foodymoody.be.member.domain.MemberProfileImage;
import com.foodymoody.be.member.domain.TasteMood;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LocalSignUpUseCase {

    private final TasteMoodReadService tasteMoodReadService;
    private final MemberWriteService memberWriteService;
    private final ImageService imageService;

    public MemberSignupResponse signUp(SignupRequest request) {
        TasteMood tasteMood = tasteMoodReadService.findById(request.getTasteMoodId());
        MemberId memberId = IdFactory.createMemberId();
        Member member = memberWriteService.create(
                memberId,
                SupportedAuthProvider.LOCAL,
                request.getEmail(),
                request.getNickname(),
                request.getPassword(),
                tasteMood,
                imageService.fetchImageDefaultProfile().getId(),
                imageService.fetchImageDefaultProfile().getProfileImageUrl(),
                LocalDateTime.now()
        );
        return MemberMapper.toSignupResponse(member.getId());
    }

}
