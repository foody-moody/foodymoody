package com.foodymoody.be.common.exception;

public class CreateTimeIsNullException extends BusinessException {

    private static final ErrorMessage errorMessage = ErrorMessage.CREATE_TIME_IS_NULL;

    public CreateTimeIsNullException() {
        super(errorMessage.getMessage());
    }

    @Override
    String getCode() {
        return errorMessage.getCode();
    }
}
