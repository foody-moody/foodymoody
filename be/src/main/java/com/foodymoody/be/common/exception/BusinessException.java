package com.foodymoody.be.common.exception;

public abstract class BusinessException extends RuntimeException {

    private final ErrorMessage errorMessage;

    protected BusinessException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return errorMessage.getMessage();
    }
}
