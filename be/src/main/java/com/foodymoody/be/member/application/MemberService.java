package com.foodymoody.be.member.application;

import com.foodymoody.be.common.exception.DuplicateMemberEmailException;
import com.foodymoody.be.common.exception.DuplicateNicknameException;
import com.foodymoody.be.common.exception.MemberNotFoundException;
import com.foodymoody.be.common.exception.UnauthorizedException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.TasteMoodId;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.application.ImageService;
import com.foodymoody.be.member.application.dto.request.ChangePasswordRequest;
import com.foodymoody.be.member.application.dto.response.NicknameDuplicationCheckResponse;
import com.foodymoody.be.member.application.dto.response.FollowInfoMemberResponse;
import com.foodymoody.be.member.application.dto.request.MemberSignupRequest;
import com.foodymoody.be.member.application.dto.response.MemberSignupResponse;
import com.foodymoody.be.member.application.dto.request.UpdateProfileRequest;
import com.foodymoody.be.member.domain.FollowRepository;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.domain.MemberRepository;
import com.foodymoody.be.member.domain.TasteMood;
import com.foodymoody.be.member.application.dto.FollowMemberSummary;
import com.foodymoody.be.member.application.dto.FeedAuthorSummary;
import com.foodymoody.be.member.domain.MemberMapper;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final TasteMoodService tasteMoodService;
    private final MemberRepository memberRepository;
    private final ImageService imageService;
    private final FollowRepository followRepository;

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
    public void updateProfile(String loginId, String id, UpdateProfileRequest request) {
        validateAuthorization(loginId, id);
        Member member = findById(IdFactory.createMemberId(id));
        // FIXME 예외가 여러개 발생 시 응답에 전부 담아서 보내기
        if (Objects.nonNull(request.getProfileImageId())
                && !Objects.equals(request.getProfileImageId(), member.getProfileImageId().getValue())) {
            Image image = imageService.findById(IdFactory.createImageId(request.getProfileImageId()));
            if (!Objects.equals(member.getProfileImageId(), ImageId.MEMBER_PROFILE_DEFAULT)) {
                // FIXME 회원 도메인 리팩토링 후 주석 해제
//                imageService.delete(loginId, member.getProfileImageId().getValue());
            }
            member.updateProfileImage(image.getId());
        }
        if (Objects.nonNull(request.getTasteMoodId())
                && !Objects.equals(request.getTasteMoodId(), member.getTasteMoodId().getValue())) {
            TasteMood tasteMood = tasteMoodService.findById(IdFactory.createTasteMoodId(request.getTasteMoodId()));
            member.changeTasteMood(tasteMood.getId());
        }
        if (Objects.nonNull(request.getNickname())
                && !Objects.equals(request.getNickname(), member.getNickname())) {
            validateNicknameDuplication(request.getNickname());
            member.changeNickname(request.getNickname());
        }
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
    }

    public Member findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname).orElseThrow(MemberNotFoundException::new);
    }

    public FeedAuthorSummary fetchFeedDataById(MemberId id) {
        return memberRepository.fetchFeedDataById(id).orElseThrow(MemberNotFoundException::new);
    }

    public void validateIdExists(MemberId id) {
        if (!memberRepository.existsById(id)) {
            throw new MemberNotFoundException();
        }
    }

    public Member findById(MemberId id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }

    public NicknameDuplicationCheckResponse checkNicknameDuplication(String nickname) {
        boolean isDuplicate = memberRepository.existsByNickname(nickname);
        return MemberMapper.toNicknameDuplicationCheckResponse(isDuplicate);
    }

    /**
     * @deprecated findById(MemberId id)를 사용해주세요
     * */
    @Deprecated(forRemoval = true)
    public Member findById(String id) {
        MemberId key = new MemberId(id);
        return memberRepository.findById(key).orElseThrow(MemberNotFoundException::new);
    }

    private void validateAuthorization(String loginId, String id) {
        if (!Objects.equals(loginId, id)) {
            throw new UnauthorizedException();
        }
    }

    @Transactional
    public void follow(String id, String targetId) {
        Member member = findById(id);
        Member target = findById(targetId);
        member.follow(target);
    }

    @Transactional
    public void unfollow(String id, String targetId) {
        Member member = findById(id);
        Member target = findById(targetId);
        member.unfollow(target);
    }

    public Slice<FollowInfoMemberResponse> listFollowings(String loginId, String id, Pageable pageable) {
        Member member = findById(id);
        Slice<FollowMemberSummary> followings = followRepository.findFollowedByFollowerOrderByCreatedAtDesc(member, pageable);
        return getFollowInfoResponses(loginId, followings);
    }

    public Slice<FollowInfoMemberResponse> listFollowers(String loginId, String id, Pageable pageable) {
        Member member = findById(id);
        Slice<FollowMemberSummary> followers = followRepository.findFollowerByFollowedOrderByCreatedAtDesc(member, pageable);
        return getFollowInfoResponses(loginId, followers);
    }

    private Slice<FollowInfoMemberResponse> getFollowInfoResponses(String loginId, Slice<FollowMemberSummary> followers) {
        if(Objects.nonNull(loginId)) {
            Member loginMember = findById(loginId);
            return MemberMapper.toFollowInfo(loginMember, followers);
        }
        return MemberMapper.toFollowInfo(followers);
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