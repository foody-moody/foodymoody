package com.foodymoody.be.feed.controller;

import com.foodymoody.be.common.annotation.MemberId;
import com.foodymoody.be.feed.dto.request.FeedRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedUpdateRequest;
import com.foodymoody.be.feed.dto.response.FeedReadAllResponse;
import com.foodymoody.be.feed.dto.response.FeedReadResponse;
import com.foodymoody.be.feed.dto.response.FeedRegisterResponse;
import com.foodymoody.be.feed.dto.response.StoreMoodResponse;
import com.foodymoody.be.feed.service.FeedService;
import com.foodymoody.be.feed.service.StoreMoodService;
import com.foodymoody.be.feed.util.FeedMapper;
import java.util.List;
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

    private final FeedService feedService;
    private final StoreMoodService storeMoodService;

    /**
     * Feed 등록
     */
    @PostMapping("/api/feeds")
    public ResponseEntity<FeedRegisterResponse> register(@RequestBody FeedRegisterRequest feedRegisterRequest,
                                                         @MemberId String memberId) {
        FeedRegisterResponse feedRegisterResponse = feedService.register(
                FeedMapper.toServiceRegisterRequest(feedRegisterRequest, memberId));
        return ResponseEntity.ok().body(feedRegisterResponse);
    }

    /**
     * 전체 Feed 조회
     */
    @GetMapping("/api/feeds")
    public ResponseEntity<Slice<FeedReadAllResponse>> readAll(Pageable pageable) {
        Slice<FeedReadAllResponse> feedReadAllResponses = feedService.readAll(pageable);
        return ResponseEntity.ok().body(feedReadAllResponses);
    }

    /**
     * 개별 Feed 조회
     */
    @GetMapping("/api/feeds/{id}")
    public ResponseEntity<FeedReadResponse> read(@PathVariable String id) {
        FeedReadResponse feedReadResponse = feedService.read(id);
        return ResponseEntity.ok().body(feedReadResponse);
    }

    /**
     * Feed 수정
     */
    @PutMapping("/api/feeds/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody FeedUpdateRequest feedUpdateRequest,
                                       @MemberId String memberId) {
        feedService.update(id, FeedMapper.toServiceUpdateRequest(feedUpdateRequest, memberId));
        return ResponseEntity.noContent().build();
    }

    /**
     * Feed 삭제
     */
    @DeleteMapping("/api/feeds/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id, @MemberId String memberId) {
        feedService.delete(FeedMapper.toServiceDeleteRequest(id, memberId));
        return ResponseEntity.noContent().build();
    }

    /**
     * 전체 Store Mood 조회
     * */
    @GetMapping("/api/feeds/store-moods")
    public ResponseEntity<List<StoreMoodResponse>> readAllStoreMood() {
        List<StoreMoodResponse> storeMoodResponses = storeMoodService.fetchAll();
        return ResponseEntity.ok().body(storeMoodResponses);
    }

}
