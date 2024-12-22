package com.foodymoody.be.common.exception;

public class ExpiredTokenException extends BusinessException {

    public ExpiredTokenException() {
        super(ErrorMessage.EXPIRED_TOKEN);
    }

}
