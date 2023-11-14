package com.foodymoody.be.mood.controller;

import com.foodymoody.be.mood.controller.dto.MoodResponse;
import com.foodymoody.be.mood.controller.dto.RandomMoodResponse;
import com.foodymoody.be.mood.service.MoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/moods")
@RequiredArgsConstructor
public class MoodController {

    private final MoodService moodService;

    @GetMapping("/random")
    public ResponseEntity<RandomMoodResponse> fetchRandom(@RequestParam(required = true) int count) {
        RandomMoodResponse response = moodService.findRandom(count);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<Slice<MoodResponse>> fetchSlice(@PageableDefault Pageable pageable) {
        Slice<MoodResponse> response = moodService.findSlice(pageable);
        return ResponseEntity.ok().body(response);
    }
}
