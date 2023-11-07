package com.foodymoody.be.common.exception;

public class ClaimNotFoundException extends BusinessException{

    public ClaimNotFoundException() {
        super(ErrorMessage.CLAIM_NOT_FOUND);
    }
}
