package com.foodymoody.be.auth.controller;

import com.foodymoody.be.auth.controller.dto.LoginRequest;
import com.foodymoody.be.auth.controller.dto.TokenIssueRequest;
import com.foodymoody.be.auth.controller.dto.TokenIssueResponse;
import com.foodymoody.be.auth.service.AuthService;
import com.foodymoody.be.common.util.HttpHeaderParser;
import com.foodymoody.be.common.util.HttpHeaderType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenIssueResponse> login(@RequestBody LoginRequest request) {
        TokenIssueResponse response = authService.login(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/token")
    public ResponseEntity<TokenIssueResponse> reissueToken(@RequestBody TokenIssueRequest request) {
        TokenIssueResponse response = authService.reIssueToken(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authorizationHeader ) {
        String accessToken = HttpHeaderParser.parse(authorizationHeader, HttpHeaderType.AUTHORIZATION);
        authService.logout(accessToken);
        return ResponseEntity.noContent().build();
    }
}
