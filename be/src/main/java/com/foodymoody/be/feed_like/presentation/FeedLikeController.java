package com.foodymoody.be.feed_like.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_like.application.dto.response.FeedLikeResponse;
import com.foodymoody.be.feed_like.application.service.FeedLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedLikeController {

    private final FeedLikeService feedLikeService;

    @PostMapping("/api/feeds/{feedStringId}/likes")
    public ResponseEntity<FeedLikeResponse> like(
            @PathVariable String feedStringId,
            @CurrentMemberId MemberId memberId
    ) {
        FeedLikeResponse feedLikeResponse = feedLikeService.like(feedStringId, memberId);
        return ResponseEntity.ok().body(feedLikeResponse);
    }

    @DeleteMapping("/api/feeds/{feedStringId}/likes")
    public ResponseEntity<Void> unlike(
            @PathVariable String feedStringId,
            @CurrentMemberId MemberId memberId
    ) {
        feedLikeService.unLike(feedStringId, memberId);
        return ResponseEntity.noContent().build();
    }

}
