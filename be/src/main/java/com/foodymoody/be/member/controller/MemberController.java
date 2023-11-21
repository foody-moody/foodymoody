package com.foodymoody.be.member.controller;

import com.foodymoody.be.member.controller.dto.MemberProfileResponse;
import com.foodymoody.be.member.controller.dto.TasteMoodResponse;
import com.foodymoody.be.member.controller.dto.MemberSignupRequest;
import com.foodymoody.be.member.controller.dto.MemberSignupResponse;
import com.foodymoody.be.member.service.MemberService;
import com.foodymoody.be.member.service.TasteMoodService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final TasteMoodService tasteMoodService;

    @PostMapping("/members")
    public ResponseEntity<MemberSignupResponse> signup(@Valid @RequestBody MemberSignupRequest request) {
        MemberSignupResponse response = memberService.create(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberProfileResponse> fetchProfile(@PathVariable String memberId) {
        MemberProfileResponse response = memberService.fetchProfile(memberId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/moods/taste")
    public ResponseEntity<List<TasteMoodResponse>> fetchAllTasteMoods() {
        List<TasteMoodResponse> response = tasteMoodService.findAll();
        return ResponseEntity.ok(response);
    }
}