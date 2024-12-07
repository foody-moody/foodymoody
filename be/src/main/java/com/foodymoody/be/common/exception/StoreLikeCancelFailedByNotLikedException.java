package com.foodymoody.be.common.exception;

public class StoreLikeCancelFailedByNotLikedException extends BusinessException {

    public StoreLikeCancelFailedByNotLikedException() {
        super(ErrorMessage.NOT_LIKED_STORE);
    }

}
