package com.foodymoody.be.member.application.usecase;

import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.TasteMoodId;
import com.foodymoody.be.image.application.service.ImageService;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.member.application.AuthorizationValidator;
import com.foodymoody.be.member.application.dto.request.UpdateProfileRequest;
import com.foodymoody.be.member.application.service.MemberReadService;
import com.foodymoody.be.member.application.service.TasteMoodReadService;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.domain.MemberProfileImage;
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

    public void updateProfile(MemberId id, UpdateProfileRequest request) {

        Member member = memberReadService.findById(id);
        ImageId requestedImageId = request.getProfileImageId();
        TasteMoodId requestedTasteMoodId = request.getTasteMoodId();
        String requestedNickname = request.getNickname();

        if (isRequestedProfileImageValid(requestedImageId, member)) {
            Image image = imageService.findById(requestedImageId);
            updateProfileImage(id, member, image);
        }
        if (isRequestedTasteMoodValid(requestedTasteMoodId, member)) {
            TasteMood tasteMood = tasteMoodReadService.findById(requestedTasteMoodId);
            member.changeTasteMood(tasteMood);
        }
        if (isRequestedNicknameValid(requestedNickname, member)) {
            memberReadService.validateNicknameDuplication(requestedNickname);
            member.changeNickname(request.getNickname());
        }
    }

    private void updateProfileImage(MemberId currentMemberId, Member member, Image image) {
        ImageId defaultImageId = MemberProfileImage.DEFAULT.getId();
        ImageId currentImageId = member.getProfileImageId();
        if (!Objects.equals(currentImageId, defaultImageId)) {
            imageService.softDelete(currentMemberId, member.getProfileImageId());
        }
        member.updateProfileImage(MemberProfileImage.of(image.getId(), image.getUrl()));
    }

    private boolean isRequestedNicknameValid(String requestedNickname, Member member) {
        return Objects.nonNull(requestedNickname)
                && !Objects.equals(requestedNickname, member.getNickname());
    }

    private boolean isRequestedTasteMoodValid(TasteMoodId requestedTasteMoodId, Member member) {
        return Objects.nonNull(requestedTasteMoodId)
                && !Objects.equals(requestedTasteMoodId, member.getTasteMoodId());
    }

    private boolean isRequestedProfileImageValid(ImageId requestedImageId, Member member) {
        return Objects.nonNull(requestedImageId)
                && !Objects.equals(requestedImageId, member.getProfileImageId());
    }

}
