package com.foodymoody.be.common.exception;

public class UnfollowFailedByNotFollowingException extends BusinessException {

    public UnfollowFailedByNotFollowingException() {
        super(ErrorMessage.NOT_FOLLOWING);
    }
}
