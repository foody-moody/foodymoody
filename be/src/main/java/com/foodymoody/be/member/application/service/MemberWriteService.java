package com.foodymoody.be.member.application.service;

import com.foodymoody.be.common.auth.SupportedAuthProvider;
import com.foodymoody.be.common.exception.DuplicateMemberEmailException;
import com.foodymoody.be.common.exception.DuplicateNicknameException;
import com.foodymoody.be.common.exception.MemberNotFoundException;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.application.dto.request.ChangePasswordRequest;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.domain.MemberProfileImage;
import com.foodymoody.be.member.domain.MemberRepository;
import com.foodymoody.be.member.domain.TasteMood;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberWriteService {

    private final MemberRepository memberRepository;

    public Member create(MemberId id, SupportedAuthProvider authProvider, String email, String nickname,
                         String password, TasteMood tasteMood, ImageId profileImageId, String profileImageUrl,
                         LocalDateTime now) {
        validateNicknameDuplication(nickname);
        validateEmailDuplication(email);
        MemberProfileImage profileImage = MemberProfileImage.of(profileImageId, profileImageUrl);
        Member forSave = Member.of(id, authProvider, email, nickname, password, profileImage, tasteMood, now);
        return memberRepository.save(forSave);
    }

    public void changePassword(MemberId id, ChangePasswordRequest request) {
        Member member = findById(id);
        member.changePassword(request.getOldPassword(), request.getPassword());
    }

    public void delete(MemberId id) {
        Member member = findById(id);
        memberRepository.delete(member);
    }

    private void validateNicknameDuplication(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException();
        }
    }

    private void validateEmailDuplication(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new DuplicateMemberEmailException();
        }
    }

    private Member findById(MemberId id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }

}
