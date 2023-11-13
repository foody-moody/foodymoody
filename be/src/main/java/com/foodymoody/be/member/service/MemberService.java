package com.foodymoody.be.member.service;

import com.foodymoody.be.common.exception.DuplicateMemberEmailException;
import com.foodymoody.be.common.exception.DuplicateNicknameException;
import com.foodymoody.be.common.exception.MemberNotFoundException;
import com.foodymoody.be.member.controller.dto.MemberProfileResponse;
import com.foodymoody.be.member.controller.dto.MemberSignupRequest;
import com.foodymoody.be.member.controller.dto.MemberSignupResponse;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.repository.MemberRepository;
import com.foodymoody.be.member.util.MemberMapper;
import com.foodymoody.be.mood.domain.Mood;
import com.foodymoody.be.mood.service.MoodService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MoodService moodService;

    @Transactional
    public MemberSignupResponse create(MemberSignupRequest request) {
        validateDuplication(request);
        Mood mood = findMoodByNameOrElseNull(request.getMood());
        String savedMemberId = memberRepository.save(MemberMapper.toEntity(request, mood)).getId();
        return MemberMapper.toSignupResponse(savedMemberId);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
    }

    private Mood findMoodByNameOrElseNull(String requested) {
        if (Objects.isNull(requested)) {
            return null;
        }
        return moodService.findMoodByName(requested);
    }

    @Transactional(readOnly = true)
    public MemberProfileResponse loadProfile(String id) {
        Member member = findById(id);
//        TODO 피드 미리보기 기능 구현 후 추가
        return MemberMapper.toMemberProfileResponse(member, List.of());
    }

    public void validateIdExists(String id) {
        if (!memberRepository.existsById(id)) {
            throw new MemberNotFoundException();
        }
    }

    public Member findById(String id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }

    private void validateDuplication(MemberSignupRequest request) {
        validateEmailDuplication(request.getEmail());
        validateNicknameDuplication(request.getNickname());
    }

    private void validateEmailDuplication(String email) {
        if(memberRepository.existsByEmail(email)){
            throw new DuplicateMemberEmailException();
        }
    }

    private void validateNicknameDuplication(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException();
        }
    }

}