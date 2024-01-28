package com.foodymoody.be.member.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.application.dto.request.ChangePasswordRequest;
import com.foodymoody.be.member.application.dto.request.MemberSignupRequest;
import com.foodymoody.be.member.application.dto.request.UpdateProfileRequest;
import com.foodymoody.be.member.application.dto.response.MemberSignupResponse;
import com.foodymoody.be.member.application.service.FollowWriteService;
import com.foodymoody.be.member.application.service.MemberWriteService;
import com.foodymoody.be.member.application.usecase.UpdateMemberProfileUseCase;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberWriteController {

    private final MemberWriteService memberWriteService;
    private final FollowWriteService followWriteService;
    private final UpdateMemberProfileUseCase updateMemberProfileUseCase;

    @PostMapping
    public ResponseEntity<MemberSignupResponse> signup(
            @Valid @RequestBody MemberSignupRequest request) {
        MemberSignupResponse response = memberWriteService.create(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Void> changePassword(
            @CurrentMemberId MemberId currentMemberId,
            @PathVariable MemberId id,
            @RequestBody ChangePasswordRequest request) {
        memberWriteService.changePassword(currentMemberId, id, request);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @CurrentMemberId MemberId currentMemberId,
            @PathVariable MemberId id) {
        memberWriteService.delete(currentMemberId, id);
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

}
