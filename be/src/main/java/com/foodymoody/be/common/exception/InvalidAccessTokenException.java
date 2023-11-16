package com.foodymoody.be.common.exception;

public class InvalidAccessTokenException extends BusinessException {

    public InvalidAccessTokenException() {
        super(ErrorMessage.INVALID_ACCESS_TOKEN);
    }
}
