package com.foodymoody.be.feed.presentation;

import com.foodymoody.be.feed.application.dto.response.StoreMoodResponse;
import com.foodymoody.be.feed.application.service.StoreMoodReadService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreMoodController {

    private final StoreMoodReadService storeMoodReadService;

    /**
     * 전체 Store Mood 조회
     */
    @GetMapping("/api/feeds/store-moods")
    public ResponseEntity<List<StoreMoodResponse>> readAllStoreMood() {
        var storeMoodResponseList = storeMoodReadService.fetchAll();
        return ResponseEntity.ok().body(storeMoodResponseList);
    }

}
