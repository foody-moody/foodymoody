package com.foodymoody.be.feed.controller;

import com.foodymoody.be.feed.dto.FeedRegisterRequest;
import com.foodymoody.be.feed.dto.FeedRegisterResponse;
import com.foodymoody.be.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @PostMapping("/api/feeds")
    public ResponseEntity<FeedRegisterResponse> registerFeed(@RequestBody FeedRegisterRequest feedRegisterRequest) {
        return ResponseEntity.ok().body(feedService.register(feedRegisterRequest));
    }
}
