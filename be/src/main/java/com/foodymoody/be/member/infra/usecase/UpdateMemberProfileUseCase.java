package com.foodymoody.be.member.infra.usecase;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.image.application.ImageService;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.member.application.AuthorizationValidator;
import com.foodymoody.be.member.application.MemberReadService;
import com.foodymoody.be.member.application.TasteMoodReadService;
import com.foodymoody.be.member.application.dto.request.UpdateProfileRequest;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.domain.TasteMood;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class UpdateMemberProfileUseCase {

    private final MemberReadService memberReadService;
    private final ImageService imageService;
    private final TasteMoodReadService tasteMoodReadService;

    public void updateProfile(MemberId currentMemberId, MemberId id, UpdateProfileRequest request) {
        AuthorizationValidator.validateAuthorization(currentMemberId, id);
        Member member = memberReadService.findById(id);
        // FIXME 예외가 여러개 발생 시 응답에 전부 담아서 보내기
        if (Objects.nonNull(request.getProfileImageId())
                && !Objects.equals(request.getProfileImageId(), member.getProfileImageId().getValue())) {
            Image image = imageService.findById(IdFactory.createImageId(request.getProfileImageId()));
            if (!Objects.equals(member.getProfileImageId(), ImageId.MEMBER_PROFILE_DEFAULT)) {
                image.softDelete(currentMemberId);
            }
            member.updateProfileImage(image.getId());
        }
        if (Objects.nonNull(request.getTasteMoodId())
                && !Objects.equals(request.getTasteMoodId(), member.getTasteMoodId().getValue())) {
            TasteMood tasteMood = tasteMoodReadService.findById(IdFactory.createTasteMoodId(request.getTasteMoodId()));
            member.changeTasteMood(tasteMood);
        }
        if (Objects.nonNull(request.getNickname())
                && !Objects.equals(request.getNickname(), member.getNickname())) {
            memberReadService.validateNicknameDuplication(request.getNickname());
            member.changeNickname(request.getNickname());
        }
    }

}
