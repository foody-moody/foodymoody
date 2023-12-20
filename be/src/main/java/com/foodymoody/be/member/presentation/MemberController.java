package com.foodymoody.be.member.presentation;

import com.foodymoody.be.common.annotation.MemberId;
import com.foodymoody.be.feed.domain.repository.dto.MemberProfileFeedPreviewResponse;
import com.foodymoody.be.member.application.dto.request.ChangePasswordRequest;
import com.foodymoody.be.member.application.dto.response.FollowInfoMemberResponse;
import com.foodymoody.be.member.application.dto.request.MemberSignupRequest;
import com.foodymoody.be.member.application.dto.response.MemberSignupResponse;
import com.foodymoody.be.member.application.dto.response.NicknameDuplicationCheckResponse;
import com.foodymoody.be.member.application.dto.response.TasteMoodResponse;
import com.foodymoody.be.member.application.dto.request.UpdateProfileRequest;
import com.foodymoody.be.member.application.dto.response.MemberProfileResponse;
import com.foodymoody.be.member.application.MemberProfileService;
import com.foodymoody.be.member.application.MemberService;
import com.foodymoody.be.member.application.TasteMoodService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final TasteMoodService tasteMoodService;
    private final MemberProfileService memberProfileService;

    @PostMapping
    public ResponseEntity<MemberSignupResponse> signup(@Valid @RequestBody MemberSignupRequest request) {
        MemberSignupResponse response = memberService.create(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberProfileResponse> fetchProfile(@MemberId String loginId, @PathVariable String id) {
        MemberProfileResponse response = memberProfileService.fetchProfile(loginId, id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}/feeds")
    public ResponseEntity<Slice<MemberProfileFeedPreviewResponse>> fetchMemberFeeds(@PathVariable String id,
                                                                                    @PageableDefault Pageable pageable) {
        Slice<MemberProfileFeedPreviewResponse> responses = memberProfileService.fetchProfileFeedPreviews(id,
                pageable);
        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("/taste-moods")
    public ResponseEntity<List<TasteMoodResponse>> fetchAllTasteMoods() {
        List<TasteMoodResponse> response = tasteMoodService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/duplication-check")
    public ResponseEntity<NicknameDuplicationCheckResponse> checkNicknameDuplication(
            @RequestParam("nickname") String nickname) {
        NicknameDuplicationCheckResponse response = memberService.checkNicknameDuplication(nickname);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Void> changePassword(@MemberId String loginId,
                                               @PathVariable String id, @RequestBody ChangePasswordRequest request) {
        memberService.changePassword(loginId, id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@MemberId String loginId, @PathVariable String id) {
        memberService.delete(loginId, id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateProfile(@MemberId String loginId, @PathVariable String id,
                                              @RequestBody UpdateProfileRequest request) {
        memberService.updateProfile(loginId, id, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/followings")
    public ResponseEntity<Void> follow(@MemberId String loginId, @PathVariable String id) {
        memberService.follow(loginId, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/followings")
    public ResponseEntity<Void> unfollow(@MemberId String loginId, @PathVariable String id) {
        memberService.unfollow(loginId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/followings")
    public ResponseEntity<Slice<FollowInfoMemberResponse>> listFollowings(@MemberId String loginId,
            @PathVariable String id, @PageableDefault Pageable pageable) {
        Slice<FollowInfoMemberResponse> response = memberService.listFollowings(loginId, id, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/followers")
    public ResponseEntity<Slice<FollowInfoMemberResponse>> listFollowers(@MemberId String loginId, @PathVariable String id, @PageableDefault Pageable pageable) {
        Slice<FollowInfoMemberResponse> response = memberService.listFollowers(loginId, id, pageable);
        return ResponseEntity.ok(response);
    }
}