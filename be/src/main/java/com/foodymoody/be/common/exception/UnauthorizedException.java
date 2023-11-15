package com.foodymoody.be.common.exception;

public class UnauthorizedException extends BusinessException{

    public UnauthorizedException() {
        super(ErrorMessage.UNAUTHORIZED);
    }

    protected UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
