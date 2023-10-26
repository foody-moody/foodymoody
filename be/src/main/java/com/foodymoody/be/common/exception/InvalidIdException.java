package com.foodymoody.be.common.exception;

public class InvalidIdException extends BusinessException {

    private static final ErrorMessage ERROR_MESSAGE = ErrorMessage.INVALID_ID;

    public InvalidIdException() {
        super(ERROR_MESSAGE.getMessage());
    }

    @Override
    String getCode() {
        return ERROR_MESSAGE.getCode();
    }
}
