package com.foodymoody.be.common.exception;

public class InvalidMemberIdException extends BusinessException {

    public InvalidMemberIdException() {
        super(ErrorMessage.INVALID_MEMBER_ID);
    }
}
