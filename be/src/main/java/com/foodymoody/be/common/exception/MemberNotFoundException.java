package com.foodymoody.be.common.exception;

public class MemberNotFoundException extends GlobalNotFoundException {

    public MemberNotFoundException() {
        super(ErrorMessage.MEMBER_NOT_FOUND);
    }
}