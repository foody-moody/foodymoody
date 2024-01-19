package com.foodymoody.be.common.exception;

public class JsonConvertException extends BusinessException {

    public JsonConvertException() {
        super(ErrorMessage.JSON_CONVERT_ERROR);
    }

}
