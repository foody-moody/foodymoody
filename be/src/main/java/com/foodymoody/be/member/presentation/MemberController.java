package com.foodymoody.be.member.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.application.FollowReadService;
import com.foodymoody.be.member.application.FollowWriteService;
import com.foodymoody.be.member.application.MemberReadService;
import com.foodymoody.be.member.application.MemberWriteService;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionsResponse;
import com.foodymoody.be.member.application.dto.response.FeedPreviewResponse;
import com.foodymoody.be.member.application.TasteMoodReadService;
import com.foodymoody.be.member.application.dto.request.ChangePasswordRequest;
import com.foodymoody.be.member.application.dto.request.MemberSignupRequest;
import com.foodymoody.be.member.application.dto.request.UpdateProfileRequest;
import com.foodymoody.be.member.application.dto.response.FollowMemberSummaryResponse;
import com.foodymoody.be.member.application.dto.response.MemberProfileResponse;
import com.foodymoody.be.member.application.dto.response.MemberSignupResponse;
import com.foodymoody.be.member.application.dto.response.NicknameDuplicationCheckResponse;
import com.foodymoody.be.member.application.dto.response.TasteMoodResponse;
import com.foodymoody.be.member.infra.usecase.UpdateMemberProfileUseCase;
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

    private final MemberReadService memberReadService;
    private final MemberWriteService memberWriteService;
    private final TasteMoodReadService tasteMoodReadService;
    private final FollowReadService followReadService;
    private final FollowWriteService followWriteService;
    private final UpdateMemberProfileUseCase updateMemberProfileUseCase;

    @PostMapping
    public ResponseEntity<MemberSignupResponse> signup(
            @Valid @RequestBody MemberSignupRequest request) {
        MemberSignupResponse response = memberWriteService.create(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberProfileResponse> fetchProfile(
            @CurrentMemberId MemberId currentMemberId,
            @PathVariable MemberId id) {
        MemberProfileResponse response = memberReadService.fetchProfile(currentMemberId, id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}/feeds")
    public ResponseEntity<Slice<FeedPreviewResponse>> fetchMemberFeeds(
            @PathVariable MemberId id,
            @PageableDefault Pageable pageable) {
        Slice<FeedPreviewResponse> responses = memberReadService.fetchFeedPreviews(id, pageable);
        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("/{id}/collections")
    public ResponseEntity<MyFeedCollectionsResponse> fetchMyCollections(
            @PathVariable MemberId id,
            @CurrentMemberId MemberId currentMemberId,
            @PageableDefault Pageable pageable) {
        MyFeedCollectionsResponse response = memberReadService.fetchMyCollections(id, currentMemberId, pageable);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/taste-moods")
    public ResponseEntity<List<TasteMoodResponse>> fetchAllTasteMoods() {
        List<TasteMoodResponse> response = tasteMoodReadService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/duplication-check")
    public ResponseEntity<NicknameDuplicationCheckResponse> checkNicknameDuplication(
            @RequestParam("nickname") String nickname) {
        NicknameDuplicationCheckResponse response = memberReadService.checkNicknameDuplication(nickname);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Void> changePassword(
            @CurrentMemberId MemberId currentMemberId,
            @PathVariable MemberId id,
            @RequestBody ChangePasswordRequest request) {
        memberWriteService.changePassword(currentMemberId, id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @CurrentMemberId MemberId currentMemberId,
            @PathVariable MemberId id) {
        memberWriteService.delete(currentMemberId, id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateProfile(
            @CurrentMemberId MemberId currentMemberId,
            @PathVariable MemberId id,
            @RequestBody UpdateProfileRequest request) {
        updateMemberProfileUseCase.updateProfile(currentMemberId, id, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/followings")
    public ResponseEntity<Void> follow(
            @CurrentMemberId MemberId currentMemberId,
            @PathVariable MemberId id) {
        followWriteService.follow(currentMemberId, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/followings")
    public ResponseEntity<Void> unfollow(
            @CurrentMemberId MemberId currentMemberId,
            @PathVariable MemberId id) {
        followWriteService.unfollow(currentMemberId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/followings")
    public ResponseEntity<Slice<FollowMemberSummaryResponse>> listFollowings(
            @CurrentMemberId MemberId currentMemberId,
            @PathVariable MemberId id,
            @PageableDefault Pageable pageable) {
        Slice<FollowMemberSummaryResponse> response = followReadService.listFollowings(currentMemberId, id, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/followers")
    public ResponseEntity<Slice<FollowMemberSummaryResponse>> listFollowers(
            @CurrentMemberId MemberId currentMemberId,
            @PathVariable MemberId id,
            @PageableDefault Pageable pageable) {
        Slice<FollowMemberSummaryResponse> response = followReadService.listFollowers(currentMemberId, id, pageable);
        return ResponseEntity.ok(response);
    }

}
