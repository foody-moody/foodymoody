package com.foodymoody.be.common.exception;

public class MemberNotFoundException extends BusinessException {

    public MemberNotFoundException() {
        super(ErrorMessage.MEMBER_NOT_FOUND);
    }
}