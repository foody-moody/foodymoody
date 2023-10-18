package com.foodymoody.be.feed.controller;

import com.foodymoody.be.feed.dto.request.FeedRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedUpdateRequest;
import com.foodymoody.be.feed.dto.response.FeedReadAllResponse;
import com.foodymoody.be.feed.dto.response.FeedReadResponse;
import com.foodymoody.be.feed.dto.response.FeedRegisterResponse;
import com.foodymoody.be.feed.service.FeedService;
import com.foodymoody.be.feed.util.FeedMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

    /**
     * 전체 Feed 조회
     */
    @GetMapping("/api/feeds")
    public ResponseEntity<List<FeedReadAllResponse>> readAllFeed() {
        List<FeedReadAllResponse> feedReadAllResponses = feedService.readAll();
        return ResponseEntity.ok().body(feedReadAllResponses);
    }

    /**
     *  Feed 등록
     */
    @PostMapping("/api/feeds")
    public ResponseEntity<FeedRegisterResponse> registerFeed(@RequestBody FeedRegisterRequest feedRegisterRequest) {
        FeedRegisterResponse feedRegisterResponse = feedService.register(
                FeedMapper.toServiceRegisterRequest(feedRegisterRequest));
        return ResponseEntity.ok().body(feedRegisterResponse);
    }

    /**
     * 개별 Feed 조회
     */
    @GetMapping("/api/feeds/{id}")
    public ResponseEntity<FeedReadResponse> readFeed(@PathVariable Long id) {
        FeedReadResponse feedReadResponse = feedService.read(id);
        return ResponseEntity.ok().body(feedReadResponse);
    }

    /**
     * Feed 수정
     */
    @PutMapping("/api/feeds/{id}")
    public ResponseEntity<Void> updateFeed(@PathVariable Long id, @RequestBody FeedUpdateRequest feedUpdateRequest) {
        feedService.update(id, FeedMapper.toServiceUpdateRequest(feedUpdateRequest));
        return ResponseEntity.noContent().build();
    }

    /**
     * Feed 삭제
     */
    @DeleteMapping("/api/feeds/{id}")
    public ResponseEntity<Void> deleteFeed(@PathVariable Long id) {
        feedService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
