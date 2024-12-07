package com.foodymoody.be.feed_collection.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.IdResponse;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionMoodId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.application.service.FeedCollectionMoodWriteService;
import com.foodymoody.be.feed_collection.presentation.dto.FeedCollectionMoodAddRequest;
import com.foodymoody.be.feed_collection.presentation.dto.FeedCollectionMoodCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedCollectionMoodWriteController {

    private final FeedCollectionMoodWriteService service;

    @PostMapping("/api/feed_collections/moods")
    public ResponseEntity<IdResponse> create(
            @RequestBody FeedCollectionMoodCreateRequest request
    ) {
        var id = service.create(request.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse.of(id));
    }

    @PostMapping("/api/feed_collections/{feedCollectionId}/moods")
    public ResponseEntity<Void> addMood(
            @PathVariable FeedCollectionId feedCollectionId,
            @CurrentMemberId MemberId memberId,
            @RequestBody FeedCollectionMoodAddRequest request
    ) {
        service.addMood(feedCollectionId, memberId, request.getMoodId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/feed_collections/{feedCollectionId}/moods/{moodId}")
    public ResponseEntity<Void> removeMood(
            @PathVariable FeedCollectionId feedCollectionId,
            @CurrentMemberId MemberId memberId,
            @PathVariable FeedCollectionMoodId moodId
    ) {
        service.removeMood(feedCollectionId, memberId, moodId);
        return ResponseEntity.noContent().build();
    }

}
