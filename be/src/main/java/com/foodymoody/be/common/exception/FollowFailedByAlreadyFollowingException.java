package com.foodymoody.be.common.exception;

public class FollowFailedByAlreadyFollowingException extends BusinessException{

    public FollowFailedByAlreadyFollowingException() {
        super(ErrorMessage.ALREADY_FOLLOWING);
    }
}
