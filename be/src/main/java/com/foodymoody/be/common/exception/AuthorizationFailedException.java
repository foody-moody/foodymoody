package com.foodymoody.be.common.exception;

public class AuthorizationFailedException extends BusinessException{

//    TODO 적절한 표준 예외로 리팩토링
    public AuthorizationFailedException() {
        super(ErrorMessage.AUTHORIZATION_FAILED);
    }
}
