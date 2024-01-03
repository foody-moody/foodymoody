package com.foodymoody.be.common.exception;

public class DuplicateNicknameException extends BusinessException {

    public DuplicateNicknameException() {
        super(ErrorMessage.DUPLICATE_MEMBER_NICKNAME);
    }
}
