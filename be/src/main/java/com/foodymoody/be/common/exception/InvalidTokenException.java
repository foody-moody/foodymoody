package com.foodymoody.be.common.exception;

public class InvalidTokenException extends BusinessException {

    public InvalidTokenException() {
        super(ErrorMessage.INVALID_TOKEN);
    }
}
