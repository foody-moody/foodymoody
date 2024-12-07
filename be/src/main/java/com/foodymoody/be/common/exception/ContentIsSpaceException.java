package com.foodymoody.be.common.exception;

public class ContentIsSpaceException extends BusinessException {

    public ContentIsSpaceException() {
        super(ErrorMessage.CONTENT_IS_SPACE);
    }

}
