package com.foodymoody.be.common.exception;

public class InvalidOAuthResponseException extends BusinessException {

    public InvalidOAuthResponseException() {
        super(ErrorMessage.INVALID_OAUTH_RESPONSE);
    }
}
