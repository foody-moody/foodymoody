package com.foodymoody.be.member.service;

import com.foodymoody.be.common.exception.DuplicateMemberEmailException;
import com.foodymoody.be.common.exception.DuplicateNicknameException;
import com.foodymoody.be.common.exception.MemberNotFoundException;
import com.foodymoody.be.common.exception.UnauthorizedException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.TasteMoodId;
import com.foodymoody.be.member.controller.dto.ChangePasswordRequest;
import com.foodymoody.be.member.controller.dto.NicknameDuplicationCheckResponse;
import com.foodymoody.be.member.controller.dto.MemberSignupRequest;
import com.foodymoody.be.member.controller.dto.MemberSignupResponse;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.domain.TasteMood;
import com.foodymoody.be.member.repository.MemberFeedData;
import com.foodymoody.be.member.repository.MemberRepository;
import com.foodymoody.be.member.util.MemberMapper;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final TasteMoodService tasteMoodService;
    private final MemberRepository memberRepository;

    @Transactional
    public MemberSignupResponse create(MemberSignupRequest request) {
        TasteMoodId tasteMoodId = IdFactory.createTasteMoodId(request.getTasteMoodId());
        TasteMood tasteMood = tasteMoodService.findById(tasteMoodId);
        validateNicknameDuplication(request.getNickname());
        validateEmailDuplication(request.getEmail());
        MemberId savedMemberId = memberRepository.save(MemberMapper.toEntity(request, tasteMood)).getId();
        return MemberMapper.toSignupResponse(savedMemberId);
    }

    @Transactional
    public void changePassword(String loginId, String id, ChangePasswordRequest request) {
        validateAuthorization(loginId, id);
        Member member = findById(IdFactory.createMemberId(id));
        member.changePassword(request.getOldPassword(), request.getNewPassword());
    }

    @Transactional
    public void delete(String loginId, String id) {
        validateAuthorization(loginId, id);
        Member member = findById(IdFactory.createMemberId(id));

        memberRepository.delete(member);
    }

    @Transactional
    public void setTasteMood(String loginId, String id, String tasteMoodId) {
        validateAuthorization(loginId, id);
        Member member = findById(IdFactory.createMemberId(id));
        TasteMood tasteMood = tasteMoodService.findById(IdFactory.createTasteMoodId(tasteMoodId));

        member.setTasteMood(tasteMood.getId());
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
    }

    public Member findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname).orElseThrow(MemberNotFoundException::new);
    }

    public MemberFeedData fetchFeedDataById(MemberId id) {
        return memberRepository.fetchFeedDataById(id).orElseThrow(MemberNotFoundException::new);
    }

    public void validateIdExists(MemberId id) {
        if (!memberRepository.existsById(id)) {
            throw new MemberNotFoundException();
        }
    }

    public Member findById(String id) {
        MemberId key = new MemberId(id);
        return memberRepository.findById(key).orElseThrow(MemberNotFoundException::new);
    }

    public Member findById(MemberId id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }

    public NicknameDuplicationCheckResponse checkNicknameDuplication(String nickname) {
        boolean isDuplicate = memberRepository.existsByNickname(nickname);
        return MemberMapper.toNicknameDuplicationCheckResponse(isDuplicate);
    }

    private void validateAuthorization(String loginId, String id) {
        if (!Objects.equals(loginId, id)) {
            throw new UnauthorizedException();
        }
    }

    private void validateEmailDuplication(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new DuplicateMemberEmailException();
        }
    }

    private void validateNicknameDuplication(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException();
        }
    }

}
