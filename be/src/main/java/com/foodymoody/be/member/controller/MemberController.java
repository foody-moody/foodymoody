package com.foodymoody.be.member.controller;

import com.foodymoody.be.common.annotation.MemberId;
import com.foodymoody.be.feed.repository.dto.MemberProfileFeedPreviewResponse;
import com.foodymoody.be.member.controller.dto.ChangePasswordRequest;
import com.foodymoody.be.member.controller.dto.MemberSignupRequest;
import com.foodymoody.be.member.controller.dto.MemberSignupResponse;
import com.foodymoody.be.member.controller.dto.NicknameDuplicationCheckResponse;
import com.foodymoody.be.member.controller.dto.TasteMoodResponse;
import com.foodymoody.be.member.repository.MemberProfileResponse;
import com.foodymoody.be.member.service.MemberProfileService;
import com.foodymoody.be.member.service.MemberService;
import com.foodymoody.be.member.service.TasteMoodService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberProfileResponse> fetchProfile(@PathVariable String memberId) {
        MemberProfileResponse response = memberProfileService.fetchProfile(memberId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{memberId}/feeds")
    public ResponseEntity<Slice<MemberProfileFeedPreviewResponse>> fetchMemberFeeds(@PathVariable String memberId,
            @PageableDefault Pageable pageable) {
        Slice<MemberProfileFeedPreviewResponse> responses = memberProfileService.fetchProfileFeedPreviews(memberId,
                pageable);
        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("/taste-moods")
    public ResponseEntity<List<TasteMoodResponse>> fetchAllTasteMoods() {
        List<TasteMoodResponse> response = tasteMoodService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/duplication-check")
    public ResponseEntity<NicknameDuplicationCheckResponse> checkNicknameDuplication(@Param("nickname") String nickname) {
        NicknameDuplicationCheckResponse response = memberService.checkNicknameDuplication(nickname);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<ChangePasswordRequest> changePassword(@MemberId String loginId,
            @PathVariable String id, @RequestBody ChangePasswordRequest request) {
        memberService.changePassword(loginId, id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@MemberId String loginId, @PathVariable String id) {
        memberService.delete(loginId, id);
        return ResponseEntity.noContent().build();
    }
}
