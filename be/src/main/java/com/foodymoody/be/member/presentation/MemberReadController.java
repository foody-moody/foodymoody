package com.foodymoody.be.member.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.application.dto.response.FeedPreviewResponse;
import com.foodymoody.be.member.application.dto.response.FollowMemberSummaryResponse;
import com.foodymoody.be.member.application.dto.response.MemberProfileResponse;
import com.foodymoody.be.member.application.dto.response.MyCollectionTitleResponse;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionsResponse;
import com.foodymoody.be.member.application.dto.response.NicknameDuplicationCheckResponse;
import com.foodymoody.be.member.application.dto.response.TasteMoodResponse;
import com.foodymoody.be.member.application.service.FollowReadService;
import com.foodymoody.be.member.application.service.MemberReadService;
import com.foodymoody.be.member.application.service.TasteMoodReadService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberReadController {

    private final MemberReadService memberReadService;
    private final TasteMoodReadService tasteMoodReadService;
    private final FollowReadService followReadService;

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

    @GetMapping("/{id}/collections/titles")
    public ResponseEntity<List<MyCollectionTitleResponse>> fetchMyCollectionTitles(
            @PathVariable MemberId id,
            @CurrentMemberId MemberId currentMemberId) {
        List<MyCollectionTitleResponse> response = memberReadService.fetchMyCollectionTitles(id, currentMemberId);
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
