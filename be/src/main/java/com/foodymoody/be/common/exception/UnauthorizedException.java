package com.foodymoody.be.common.exception;

public class UnauthorizedException extends BusinessException{

//    TODO 적절한 표준 예외로 리팩토링
    public UnauthorizedException() {
        super(ErrorMessage.UNAUTHORIZED);
    }
}
