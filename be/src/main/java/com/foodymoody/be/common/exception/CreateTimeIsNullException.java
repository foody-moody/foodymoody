package com.foodymoody.be.common.exception;

public class CreateTimeIsNullException extends BusinessException {

    public CreateTimeIsNullException() {
        super(ErrorMessage.CREATE_TIME_IS_NULL);
    }

}
