package com.foodymoody.be.common.exception;

public class InvalidReconfirmPasswordException extends BusinessException {

    public InvalidReconfirmPasswordException() {
        super(ErrorMessage.INVALID_CONFIRM_PASSWORD);
    }

}
