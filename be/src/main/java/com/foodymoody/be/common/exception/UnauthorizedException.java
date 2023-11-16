package com.foodymoody.be.common.exception;

public class UnauthorizedException extends RuntimeException{

    private final ErrorMessage errorMessage;

    protected UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return errorMessage.getCode();
    }
}
