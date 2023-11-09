package com.foodymoody.be.common.exception;

public class UnsupportedClaimException extends CustomIllegalArgumentException{

    public UnsupportedClaimException() {
        super(ErrorMessage.CLAIM_NOT_EXISTS);
    }
}
