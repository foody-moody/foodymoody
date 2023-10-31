package com.foodymoody.be.common.exception;

public class ContentIsOver200Exception extends BusinessException {

    public ContentIsOver200Exception() {
        super(ErrorMessage.CONTENT_IS_OVER_200);
    }

}
