package com.foodymoody.be.common.exception;

public class DuplicateMoodException extends BusinessException {

    public DuplicateMoodException() {
        super(ErrorMessage.DUPLICATE_MOOD);
    }

}
