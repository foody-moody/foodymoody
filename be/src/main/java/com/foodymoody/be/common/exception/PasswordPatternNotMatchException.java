package com.foodymoody.be.common.exception;

public class PasswordPatternNotMatchException extends BusinessException {

    public PasswordPatternNotMatchException() {
        super(ErrorMessage.PASSWORD_PATTERN_NOT_MATCH);
    }

}
