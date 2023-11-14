package com.foodymoody.be.mood.controller;

import com.foodymoody.be.mood.controller.dto.MoodRegisterRequest;
import com.foodymoody.be.mood.controller.dto.MoodRegisterResponse;
import com.foodymoody.be.mood.controller.dto.MoodResponse;
import com.foodymoody.be.mood.controller.dto.RandomMoodResponse;
import com.foodymoody.be.mood.service.MoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public ResponseEntity<MoodRegisterResponse> register(@RequestBody MoodRegisterRequest request) {
        MoodRegisterResponse response = moodService.create(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoodResponse> fetchById(@PathVariable String id) {
        MoodResponse response = moodService.findById(id);
        return ResponseEntity.ok().body(response);
    }
}
