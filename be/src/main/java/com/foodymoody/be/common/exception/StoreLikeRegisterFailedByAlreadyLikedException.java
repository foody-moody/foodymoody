package com.foodymoody.be.common.exception;

public class StoreLikeRegisterFailedByAlreadyLikedException extends BusinessException {

    public StoreLikeRegisterFailedByAlreadyLikedException() {
        super(ErrorMessage.ALREADY_LIKED_STORE);
    }

}
