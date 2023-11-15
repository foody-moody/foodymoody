package com.foodymoody.be.common.exception;

public abstract class GlobalNotFoundException extends BusinessException{
//  TODO 더 적절한 네이밍으로 수정

    protected GlobalNotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
