package com.foodymoody.be.common.exception;

public class RequestHeaderNotFoundException extends BusinessException {

    public RequestHeaderNotFoundException() {
        super(ErrorMessage.REQUEST_HEADER_NOT_FOUND);
    }

}
