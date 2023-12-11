package com.foodymoody.be.feed_heart.controller;

import com.foodymoody.be.common.annotation.MemberId;
import com.foodymoody.be.feed_heart.dto.request.FeedHeartRequest;
import com.foodymoody.be.feed_heart.dto.response.FeedHeartResponse;
import com.foodymoody.be.feed_heart.service.FeedHeartService;
import com.foodymoody.be.feed_heart.util.FeedHeartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedHeartController {

    private final FeedHeartService feedHeartService;

    @PostMapping("/api/feeds/{feedStringId}/likes")
    public ResponseEntity<FeedHeartResponse> like(@PathVariable String feedStringId, @MemberId String memberStringId) {
        FeedHeartResponse feedHeartResponse = feedHeartService.like(feedStringId, memberStringId);
        return ResponseEntity.ok().body(feedHeartResponse);
    }

    @DeleteMapping("/api/feeds/{feedStringId}/likes")
    public ResponseEntity<Void> unlike(@PathVariable String feedStringId, @MemberId String memberStringId) {
        feedHeartService.unLike(feedStringId, memberStringId);
        return ResponseEntity.noContent().build();
    }

}
