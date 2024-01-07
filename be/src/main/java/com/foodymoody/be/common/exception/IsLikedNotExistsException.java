package com.foodymoody.be.common.exception;

public class IsLikedNotExistsException extends BusinessException {

    public IsLikedNotExistsException() {
        super(ErrorMessage.IS_LIKED_NOT_EXISTS);
    }

}
