package com.foodymoody.be.member.application;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.common.exception.UnauthorizedException;
import com.foodymoody.be.common.util.ids.MemberId;
import java.util.Objects;

public class AuthorizationValidator {

    private AuthorizationValidator() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static void validateAuthorization(MemberId currentMemberId, MemberId id) {
        if (!Objects.equals(currentMemberId, id)) {
            throw new UnauthorizedException();
        }
    }

}
