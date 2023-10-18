package com.foodymoody.be.feed.controller;

import com.foodymoody.be.feed.dto.request.FeedRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedUpdateRequest;
import com.foodymoody.be.feed.dto.response.FeedRegisterResponse;
import com.foodymoody.be.feed.service.FeedService;
import com.foodymoody.be.feed.util.FeedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @PostMapping("/api/feeds")
    public ResponseEntity<FeedRegisterResponse> registerFeed(@RequestBody FeedRegisterRequest feedRegisterRequest) {
        FeedRegisterResponse feedRegisterResponse = feedService.register(
                FeedMapper.toServiceRegisterRequest(feedRegisterRequest));
        return ResponseEntity.ok().body(feedRegisterResponse);
    }

    @PutMapping("/api/feeds/{id}")
    public ResponseEntity<Void> updateFeed(@PathVariable Long id, @RequestBody FeedUpdateRequest feedUpdateRequest) {
        feedService.update(id, FeedMapper.toServiceUpdateRequest(feedUpdateRequest));
        return ResponseEntity.noContent().build();
    }

}
