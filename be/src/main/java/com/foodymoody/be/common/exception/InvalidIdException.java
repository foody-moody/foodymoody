package com.foodymoody.be.common.exception;

public class InvalidIdException extends BusinessException {

    public InvalidIdException() {
        super(ErrorMessage.INVALID_ID);
    }

}
