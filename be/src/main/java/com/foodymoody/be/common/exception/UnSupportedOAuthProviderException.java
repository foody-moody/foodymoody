package com.foodymoody.be.common.exception;

public class UnSupportedOAuthProviderException extends BusinessException {

    public UnSupportedOAuthProviderException() {
        super(ErrorMessage.UNSUPPORTED_OAUTH_PROVIDER);
    }

}
