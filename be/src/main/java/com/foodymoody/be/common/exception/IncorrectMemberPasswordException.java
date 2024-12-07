package com.foodymoody.be.common.exception;

public class IncorrectMemberPasswordException extends UnauthorizedException {

    public IncorrectMemberPasswordException() {
        super(ErrorMessage.MEMBER_INCORRECT_PASSWORD);
    }

}
