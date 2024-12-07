package com.foodymoody.be.common.exception;

public class DuplicateMemberEmailException extends BusinessException {

    public DuplicateMemberEmailException() {
        super(ErrorMessage.DUPLICATE_MEMBER_EMAIL);
    }

}
