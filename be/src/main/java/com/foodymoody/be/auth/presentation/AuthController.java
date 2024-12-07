package com.foodymoody.be.auth.presentation;

import com.foodymoody.be.auth.application.dto.request.LoginRequest;
import com.foodymoody.be.auth.application.dto.request.TokenIssueRequest;
import com.foodymoody.be.auth.application.dto.response.TokenIssueResponse;
import com.foodymoody.be.auth.application.usecase.LocalLoginUseCase;
import com.foodymoody.be.auth.application.usecase.LogoutUseCase;
import com.foodymoody.be.auth.application.usecase.OAuthLoginUseCase;
import com.foodymoody.be.auth.application.usecase.TokenReissueUseCase;
import com.foodymoody.be.common.util.HttpHeaderParser;
import com.foodymoody.be.common.util.HttpHeaderType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LogoutUseCase logoutUseCase;
    private final LocalLoginUseCase localLoginUseCase;
    private final TokenReissueUseCase tokenReissueUseCase;
    private final OAuthLoginUseCase oAuthLoginUseCase;

    @PostMapping("/login")
    public ResponseEntity<TokenIssueResponse> login(@RequestBody LoginRequest request) {
        TokenIssueResponse response = localLoginUseCase.login(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/oauth/{provider}")
    public ResponseEntity<TokenIssueResponse> oAuthLogin(
            @PathVariable String provider,
            @RequestParam("code") String code) {
        TokenIssueResponse response = oAuthLoginUseCase.login(provider, code);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/token")
    public ResponseEntity<TokenIssueResponse> reissueToken(@RequestBody TokenIssueRequest request) {
        TokenIssueResponse response = tokenReissueUseCase.reIssueToken(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authorizationHeader) {
        String accessToken = HttpHeaderParser.parse(authorizationHeader, HttpHeaderType.AUTHORIZATION);
        logoutUseCase.logout(accessToken);
        return ResponseEntity.noContent().build();
    }

}
