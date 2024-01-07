package com.foodymoody.be.feed.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.FeedMapper;
import com.foodymoody.be.feed.application.dto.request.FeedRegisterRequest;
import com.foodymoody.be.feed.application.dto.request.FeedUpdateRequest;
import com.foodymoody.be.feed.application.dto.response.FeedReadAllResponse;
import com.foodymoody.be.feed.application.dto.response.FeedReadResponse;
import com.foodymoody.be.feed.application.dto.response.FeedRegisterResponse;
import com.foodymoody.be.feed.infra.usecase.FeedUseCase;
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
            @RequestBody FeedRegisterRequest feedRegisterRequest,
            @CurrentMemberId MemberId memberId) {
        FeedRegisterResponse feedRegisterResponse = feedUseCase.register(
                FeedMapper.toServiceRegisterRequest(feedRegisterRequest, memberId));
        return ResponseEntity.ok().body(feedRegisterResponse);
    }

    /**
     * 전체 Feed 조회
     */
    @GetMapping("/api/feeds")
    public ResponseEntity<Slice<FeedReadAllResponse>> readAll(Pageable pageable,
                                                              @CurrentMemberId MemberId memberId) {
        Slice<FeedReadAllResponse> feedReadAllResponses = feedUseCase.readAll(pageable, memberId);
        return ResponseEntity.ok().body(feedReadAllResponses);
    }

    /**
     * 개별 Feed 조회
     */
    @GetMapping("/api/feeds/{id}")
    public ResponseEntity<FeedReadResponse> read(@PathVariable String id,
                                                 @CurrentMemberId MemberId memberId) {
        FeedReadResponse feedReadResponse = feedUseCase.read(id, memberId);
        return ResponseEntity.ok().body(feedReadResponse);
    }

    /**
     * Feed 수정
     */
    @PutMapping("/api/feeds/{id}")
    public ResponseEntity<Void> update(@PathVariable FeedId id, @RequestBody FeedUpdateRequest feedUpdateRequest,
                                       @CurrentMemberId MemberId memberId) {
        feedUseCase.update(id, FeedMapper.toServiceUpdateRequest(feedUpdateRequest, memberId));
        return ResponseEntity.noContent().build();
    }

    /**
     * Feed 삭제
     */
    @DeleteMapping("/api/feeds/{id}")
    public ResponseEntity<Void> delete(@PathVariable FeedId id,
                                       @CurrentMemberId MemberId memberId) {
        feedUseCase.delete(FeedMapper.toServiceDeleteRequest(id, memberId));
        return ResponseEntity.noContent().build();
    }

}
