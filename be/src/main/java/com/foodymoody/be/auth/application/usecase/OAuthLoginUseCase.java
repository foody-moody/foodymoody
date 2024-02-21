package com.foodymoody.be.auth.application.usecase;

import com.foodymoody.be.auth.application.dto.OAuthMemberDetails;
import com.foodymoody.be.auth.application.dto.response.TokenIssueResponse;
import com.foodymoody.be.auth.application.service.OAuthClientManager;
import com.foodymoody.be.auth.application.service.TokenService;
import com.foodymoody.be.auth.infra.client.OAuthClient;
import com.foodymoody.be.common.auth.AuthProvider;
import com.foodymoody.be.common.auth.SupportedAuthProvider;
import com.foodymoody.be.common.exception.DuplicateMemberEmailException;
import com.foodymoody.be.common.exception.UnSupportedOAuthProviderException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.image.application.service.ImageService;
import com.foodymoody.be.member.application.service.MemberReadService;
import com.foodymoody.be.member.application.service.MemberWriteService;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OAuthLoginUseCase {

    private final MemberReadService memberReadService;
    private final MemberWriteService memberWriteService;
    private final OAuthClientManager oAuthClientManager;
    private final ImageService imageService;
    private final TokenService tokenService;

    public TokenIssueResponse login(String providerName, String authorizationCode) {
        SupportedAuthProvider authProvider = getProviderIfSupported(providerName);
        OAuthMemberDetails oAuthMemberDetails = getOAuthMemberDetails(authProvider, authorizationCode);
        // TODO 리팩토링 필요
        var member = memberReadService.findByEmail(oAuthMemberDetails.getEmail())
                .orElseGet(
                        () -> {
                            MemberId memberId = IdFactory.createMemberId();
                            var savedImage = imageService.saveOAuthMemberProfileImage(memberId, oAuthMemberDetails.getProfileImageUrl());
                            return memberWriteService.create(
                                    memberId,
                                    authProvider,
                                    oAuthMemberDetails.getEmail(),
                                    oAuthMemberDetails.getNickname(),
                                    null,
                                    null,
                                    savedImage.getId(),
                                    savedImage.getUrl(),
                                    LocalDateTime.now()
                            );
                        }
                );
        if (!Objects.equals(member.getAuthProvider(), authProvider)) {
            throw new DuplicateMemberEmailException();
        }
        Date now = new Date();
        return tokenService.issue(now, member);
    }

    private OAuthMemberDetails getOAuthMemberDetails(AuthProvider providerType, String authorizationCode) {
        OAuthClient client = oAuthClientManager.getByProviderType(providerType);
        String oAuthAccessToken = client.getOauthToken(authorizationCode).getAccessToken();
        return client.getOAuthMemberDetails(oAuthAccessToken);
    }

    private SupportedAuthProvider getProviderIfSupported(String providerName) {
        return Arrays.stream(SupportedAuthProvider.values())
                .filter(p -> Objects.equals(p.getName(), providerName))
                .findFirst()
                .orElseThrow(UnSupportedOAuthProviderException::new);
    }

}
