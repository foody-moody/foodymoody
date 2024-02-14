package com.foodymoody.be.member.application.service;

import com.foodymoody.be.common.exception.DuplicateMemberEmailException;
import com.foodymoody.be.common.exception.DuplicateNicknameException;
import com.foodymoody.be.common.exception.MemberNotFoundException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.application.dto.request.ChangePasswordRequest;
import com.foodymoody.be.member.application.dto.request.SignupRequest;
import com.foodymoody.be.member.application.dto.response.MemberSignupResponse;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.domain.MemberMapper;
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
    private final TasteMoodReadService tasteMoodReadService;

    public MemberSignupResponse create(SignupRequest request) {
        TasteMood tasteMood = tasteMoodReadService.findById(request.getTasteMoodId());
        validateNicknameDuplication(request.getNickname());
        validateEmailDuplication(request.getEmail());
        Member forSave = createEntity(request, tasteMood);
        MemberId savedMemberId = memberRepository.save(forSave).getId();
        return MemberMapper.toSignupResponse(savedMemberId);
    }

    public void changePassword(MemberId id, ChangePasswordRequest request) {
        Member member = findById(id);
        member.changePassword(request.getOldPassword(), request.getPassword());
    }

    public void delete(MemberId id) {
        Member member = findById(id);
        memberRepository.delete(member);
    }

    private Member createEntity(SignupRequest request, TasteMood tasteMood) {
        MemberId id = IdFactory.createMemberId();
        LocalDateTime now = LocalDateTime.now();
        return MemberMapper.toEntity(id, request, tasteMood, now);
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
