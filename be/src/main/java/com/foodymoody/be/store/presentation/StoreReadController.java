package com.foodymoody.be.store.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store.application.dto.response.StoreFeedPreviewResponse;
import com.foodymoody.be.store.application.service.StoreReadService;
import com.foodymoody.be.store.application.service.dto.response.StoreDetailsResponse;
import com.foodymoody.be.store.application.service.dto.response.StoreSearchResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreReadController {

    private final StoreReadService service;

    @GetMapping("/{id}")
    public ResponseEntity<StoreDetailsResponse> fetchDetails(
            @PathVariable StoreId id,
            @CurrentMemberId MemberId currentMemberId
    ) {
        StoreDetailsResponse response = service.fetchDetails(id, currentMemberId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<StoreSearchResponse>> search(
            @RequestParam("query") String query
    ) {
        List<StoreSearchResponse> response = service.search(query);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/feeds")
    public ResponseEntity<Slice<StoreFeedPreviewResponse>> fetchFeedPreviews(
            @PathVariable StoreId id,
            @PageableDefault Pageable pageable
    ) {
        Slice<StoreFeedPreviewResponse> responses = service.fetchStoreFeedResponses(id, pageable);
        return ResponseEntity.ok(responses);
    }

}
