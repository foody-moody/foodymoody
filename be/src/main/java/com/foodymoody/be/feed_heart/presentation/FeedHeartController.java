package com.foodymoody.be.feed_heart.presentation;

import com.foodymoody.be.common.annotation.MemberId;
import com.foodymoody.be.feed_heart.application.FeedHeartService;
import com.foodymoody.be.feed_heart.application.dto.response.FeedHeartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
