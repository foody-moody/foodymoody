package com.foodymoody.be.feed.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.FeedMapper;
import com.foodymoody.be.feed.application.dto.response.CollectionReadFeedDetailsResponse;
import com.foodymoody.be.feed.application.usecase.CollectionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionUseCase collectionUseCase;

    /**
     * 컬렉션 상세 페이지 피드 조회 API 컬렉션 상세 - "피드들 (n)" 파트
     */
    @GetMapping("/api/feed_collections/{collectionId}/feeds")
    public ResponseEntity<Slice<CollectionReadFeedDetailsResponse>> readCollectionFeedDetails(
            @PathVariable FeedCollectionId collectionId,
            Pageable pageable,
            @CurrentMemberId MemberId memberId
    ) {
        var collectionReadFeedDetailsServiceRequest = FeedMapper.toCollectionReadFeedDetailsServiceRequest(
                collectionId,
                pageable,
                memberId
        );
        var collectionReadFeedDetailsResponseSlice = collectionUseCase.readCollectionFeedDetails(
                collectionReadFeedDetailsServiceRequest
        );
        return ResponseEntity.ok().body(collectionReadFeedDetailsResponseSlice);
    }

}
