package com.foodymoody.be.common.exception;

public abstract class BusinessException extends RuntimeException {

    protected BusinessException(String message) {
        super(message);
    }

    abstract String getCode();
}
