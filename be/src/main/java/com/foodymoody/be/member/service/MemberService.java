package com.foodymoody.be.member.service;

import com.foodymoody.be.common.exception.DuplicateMemberEmailException;
import com.foodymoody.be.common.exception.DuplicateNicknameException;
import com.foodymoody.be.common.exception.MemberNotFoundException;
import com.foodymoody.be.member.controller.dto.MemberProfileResponse;
import com.foodymoody.be.member.controller.dto.MemberSignupRequest;
import com.foodymoody.be.member.controller.dto.MemberSignupResponse;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.repository.MemberFeedData;
import com.foodymoody.be.member.repository.MemberProfileData;
import com.foodymoody.be.member.repository.MemberRepository;
import com.foodymoody.be.member.util.MemberMapper;
import com.foodymoody.be.mood.service.MoodService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MoodService moodService;

    @Transactional
    public MemberSignupResponse create(MemberSignupRequest request) {
        validateNicknameDuplication(request.getNickname());
        validateEmailDuplication(request.getEmail());
        String moodId = findMoodIdByNameOrElseNull(request.getMood());
        String savedMemberId = memberRepository.save(MemberMapper.toEntity(request, moodId)).getId();
        return MemberMapper.toSignupResponse(savedMemberId);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
    }

    public MemberFeedData findFeedDataById(String id) {
        return memberRepository.loadFeedDataById(id).orElseThrow(MemberNotFoundException::new);
    }

    public MemberProfileResponse loadProfile(String id) {
        MemberProfileData data = findProfileData(id);
//        TODO 피드 미리보기 기능 구현 후 추가
        return MemberMapper.toMemberProfileResponse(data, List.of());
    }

    public void validateIdExists(String id) {
        WrappedId key = new WrappedId(id);
        if (!memberRepository.existsById(key)) {
            throw new MemberNotFoundException();
        }
    }

    public Member findById(String id) {
        WrappedId key = new WrappedId(id);
        return memberRepository.findById(key).orElseThrow(MemberNotFoundException::new);
    }

    private String findMoodIdByNameOrElseNull(String requested) {
        if (Objects.isNull(requested)) {
            return null;
        }
        return moodService.findMoodByName(requested).getId();
    }

    private MemberProfileData findProfileData(String id) {
        return memberRepository.loadProfileDataById(id)
                .orElseThrow(MemberNotFoundException::new);
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