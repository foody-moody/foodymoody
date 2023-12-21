package com.foodymoody.be.member.application;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.TasteMoodId;
import com.foodymoody.be.member.application.dto.request.ChangePasswordRequest;
import com.foodymoody.be.member.application.dto.request.MemberSignupRequest;
import com.foodymoody.be.member.application.dto.response.MemberSignupResponse;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.domain.MemberMapper;
import com.foodymoody.be.member.domain.MemberRepository;
import com.foodymoody.be.member.domain.TasteMood;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberWriteService {

    private final MemberQueryService memberQueryService;
    private final MemberRepository memberRepository;
    private final TasteMoodReadService tasteMoodReadService;

    public MemberSignupResponse create(MemberSignupRequest request) {
        TasteMoodId tasteMoodId = IdFactory.createTasteMoodId(request.getTasteMoodId());
        TasteMood tasteMood = tasteMoodReadService.findById(tasteMoodId);
        memberQueryService.validateNicknameDuplication(request.getNickname());
        memberQueryService.validateEmailDuplication(request.getEmail());
        MemberId savedMemberId = memberRepository.save(MemberMapper.toEntity(request, tasteMood)).getId();
        return MemberMapper.toSignupResponse(savedMemberId);
    }

    public void changePassword(MemberId currentMemberId, MemberId id, ChangePasswordRequest request) {
        AuthorizationValidator.validateAuthorization(currentMemberId, id);
        Member member = memberQueryService.findById(id);
        member.changePassword(request.getOldPassword(), request.getNewPassword());
    }

    public void delete(MemberId currentMemberId, MemberId id) {
        AuthorizationValidator.validateAuthorization(currentMemberId, id);
        Member member = memberQueryService.findById(id);
        memberRepository.delete(member);
    }


}
