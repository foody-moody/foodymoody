package com.foodymoody.be.feed.presentation;

import com.foodymoody.be.feed.application.StoreMoodService;
import com.foodymoody.be.feed.application.dto.response.StoreMoodResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreMoodController {

    private final StoreMoodService storeMoodService;

    /**
     * 전체 Store Mood 조회
     */
    @GetMapping("/api/feeds/store-moods")
    public ResponseEntity<List<StoreMoodResponse>> readAllStoreMood() {
        List<StoreMoodResponse> storeMoodResponses = storeMoodService.fetchAll();
        return ResponseEntity.ok().body(storeMoodResponses);
    }

}