package com.foodymoody.be.auth.infra.client;

import com.foodymoody.be.auth.application.dto.OAuthMemberDetails;
import com.foodymoody.be.auth.infra.dto.OAuthTokenResponse;
import com.foodymoody.be.common.exception.InvalidOAuthResponseException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GoogleClient implements OAuthClient {

    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String tokenUri;
    private final String userInfoUri;
    private final String emailFieldName;
    private final String nicknameFieldName;
    private final String profileImageFieldName;

    public GoogleClient(
            @Value("${oauth2.google.client-id}")
            String clientId,
            @Value("${oauth2.google.client-secret}")
            String clientSecret,
            @Value("${oauth2.google.redirect-uri}")
            String redirectUri,
            @Value("${oauth2.google.token-uri}")
            String tokenUri,
            @Value("${oauth2.google.user-info-uri}")
            String userInfoUri,
            @Value("${oauth2.google.email-field-name}")
            String emailFieldName,
            @Value("${oauth2.google.nickname-field-name}")
            String nicknameFieldName,
            @Value("${oauth2.google.profile-image-field-name}")
            String profileImageFieldName
    ) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.tokenUri = tokenUri;
        this.userInfoUri = userInfoUri;
        this.emailFieldName = emailFieldName;
        this.nicknameFieldName = nicknameFieldName;
        this.profileImageFieldName = profileImageFieldName;
    }

    @Override
    public OAuthMemberDetails getOAuthMemberDetails(String accessToken) {
        Map<String, Object> userInfo = WebClient.create()
                .get()
                .uri(userInfoUri)
                .headers(header -> header.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .block();

        return OAuthMemberDetails.of(
                String.valueOf(userInfo.get(emailFieldName)),
                String.valueOf(userInfo.get(nicknameFieldName)),
                String.valueOf(userInfo.get(profileImageFieldName)
                ));
    }

    private MultiValueMap<String, String> createTokenRequest(String authorizationCode) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", authorizationCode);
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", redirectUri); // 하드코딩
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        return formData;
    }

    @Override
    public OAuthTokenResponse getOauthToken(String authorizationCode) {
        try {
            log.info("Sending token request to: {}", tokenUri);
            MultiValueMap<String, String> formData = createTokenRequest(authorizationCode);
            log.info("Request form data: {}", formData);

            return WebClient.create()
                    .post()
                    .uri(tokenUri)
                    .headers(header -> {
                        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                        header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                    })
                    .bodyValue(formData)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                        log.error("Error response status: {}", clientResponse.statusCode());
                        return clientResponse.bodyToMono(String.class).flatMap(body -> {
                            log.error("Error response body: {}", body);
                            return Mono.error(new RuntimeException("4xx error"));
                        });
                    })
                    .bodyToMono(OAuthTokenResponse.class)
                    .doOnNext(response -> log.info("Token response: {}", response))
                    .doOnError(error -> log.error("Error during getOauthToken", error))
                    .doOnSuccess(response -> log.info("Successfully got oauth token")) // 성공 로그 추가
                    .block();
        } catch (RuntimeException e) {
            log.error("Error during getOauthToken", e);
            throw new InvalidOAuthResponseException();
        }
    }

}

