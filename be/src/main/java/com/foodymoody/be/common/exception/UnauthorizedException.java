package com.foodymoody.be.common.exception;

public class UnauthorizedException extends RuntimeException{

    private final ErrorMessage errorMessage;

    public UnauthorizedException() {
        super(ErrorMessage.UNAUTHORIZED.getMessage());
        this.errorMessage = ErrorMessage.UNAUTHORIZED;
    }

    protected UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return errorMessage.getCode();
    }
}
