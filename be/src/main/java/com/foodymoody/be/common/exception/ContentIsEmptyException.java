package com.foodymoody.be.common.exception;

public class ContentIsEmptyException extends BusinessException {


    public ContentIsEmptyException() {
        super(ErrorMessage.CONTENT_IS_EMPTY);
    }
}
