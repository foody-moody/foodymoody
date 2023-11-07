package com.foodymoody.be.common.exception;

public class CustomIllegalArgumentException extends IllegalArgumentException{

    private final ErrorMessage errorMessage;

    protected CustomIllegalArgumentException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return errorMessage.getMessage();
    }
}
