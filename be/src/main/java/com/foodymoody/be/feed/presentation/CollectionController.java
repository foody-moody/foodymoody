package com.foodymoody.be.feed.presentation;

import com.foodymoody.be.feed.application.FeedMapper;
import com.foodymoody.be.feed.application.dto.response.CollectionReadAllFeedResponse;
import com.foodymoody.be.feed.infra.usecase.CollectionUseCase;
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
     * 컬렉션 상세 페이지 피드 조회 API
     */
    @GetMapping("/api/collections/{collectionId}/feeds")
    public ResponseEntity<Slice<CollectionReadAllFeedResponse>> readAll(@PathVariable String collectionId, Pageable pageable) {
        Slice<CollectionReadAllFeedResponse> collectionReadAllResponses = collectionUseCase.readAll(
                FeedMapper.toCollectionServiceReadAllFeedRequest(collectionId, pageable));
        return ResponseEntity.ok().body(collectionReadAllResponses);
    }

}
