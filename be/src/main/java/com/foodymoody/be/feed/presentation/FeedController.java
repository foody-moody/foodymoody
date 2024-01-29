package com.foodymoody.be.feed.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.FeedMapper;
import com.foodymoody.be.feed.application.dto.request.FeedRegisterRequest;
import com.foodymoody.be.feed.application.dto.request.FeedServiceDeleteRequest;
import com.foodymoody.be.feed.application.dto.request.FeedUpdateRequest;
import com.foodymoody.be.feed.application.dto.response.FeedReadAllResponse;
import com.foodymoody.be.feed.application.dto.response.FeedReadResponse;
import com.foodymoody.be.feed.application.dto.response.FeedRegisterResponse;
import com.foodymoody.be.feed.application.usecase.FeedUseCase;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FeedController {

    private final FeedUseCase feedUseCase;

    /**
     * Feed 등록
     */
    @PostMapping("/api/feeds")
    public ResponseEntity<FeedRegisterResponse> register(
            @RequestBody @Valid FeedRegisterRequest feedRegisterRequest,
            @CurrentMemberId MemberId memberId
    ) {
        var feedServiceRegisterRequest = FeedMapper.toServiceRegisterRequest(feedRegisterRequest, memberId);
        FeedRegisterResponse feedRegisterResponse = feedUseCase.register(feedServiceRegisterRequest);
        return ResponseEntity.ok().body(feedRegisterResponse);
    }

    /**
     * 전체 Feed 조회
     */
    @GetMapping("/api/feeds")
    public ResponseEntity<Slice<FeedReadAllResponse>> readAll(
            Pageable pageable,
            @CurrentMemberId MemberId memberId
    ) {
        var feedReadAllResponseSlice = feedUseCase.readAll(pageable, memberId);
        return ResponseEntity.ok().body(feedReadAllResponseSlice);
    }

    /**
     * 개별 Feed 조회
     */
    @GetMapping("/api/feeds/{id}")
    public ResponseEntity<FeedReadResponse> read(
            @PathVariable FeedId id,
            @CurrentMemberId MemberId memberId
    ) {
        var feedReadResponse = feedUseCase.read(id, memberId);
        return ResponseEntity.ok().body(feedReadResponse);
    }

    /**
     * Feed 수정
     */
    @PutMapping("/api/feeds/{id}")
    public ResponseEntity<Void> update(
            @PathVariable FeedId id,
            @RequestBody @Valid FeedUpdateRequest feedUpdateRequest,
            @CurrentMemberId MemberId memberId
    ) {
        var feedServiceUpdateRequest = FeedMapper.toServiceUpdateRequest(
                id,
                feedUpdateRequest,
                memberId
        );
        feedUseCase.update(feedServiceUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    /**
     * Feed 삭제
     */
    @DeleteMapping("/api/feeds/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable FeedId id,
            @CurrentMemberId MemberId memberId
    ) {
        var feedServiceDeleteRequest = FeedMapper.toServiceDeleteRequest(id, memberId);
        feedUseCase.delete(feedServiceDeleteRequest);
        return ResponseEntity.noContent().build();
    }

}
