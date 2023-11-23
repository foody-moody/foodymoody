package com.foodymoody.be.heart.controller;

import com.foodymoody.be.common.annotation.MemberId;
import com.foodymoody.be.heart.dto.request.HeartRequest;
import com.foodymoody.be.heart.dto.response.HeartResponse;
import com.foodymoody.be.heart.service.HeartService;
import com.foodymoody.be.heart.util.HeartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HeartController {

    private final HeartService heartService;

    @PostMapping("/api/likes")
    public ResponseEntity<HeartResponse> like(@RequestBody HeartRequest heartRequest,
                                              @MemberId String memberId) {
        HeartResponse heartResponse = heartService.like(HeartMapper.toHeartServiceRequest(heartRequest, memberId));
        return ResponseEntity.ok().body(heartResponse);
    }

}
