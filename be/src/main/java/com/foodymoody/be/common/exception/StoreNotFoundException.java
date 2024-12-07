package com.foodymoody.be.common.exception;

public class StoreNotFoundException extends BusinessException {

    public StoreNotFoundException() {
        super(ErrorMessage.STORE_NOT_FOUND);
    }

}
