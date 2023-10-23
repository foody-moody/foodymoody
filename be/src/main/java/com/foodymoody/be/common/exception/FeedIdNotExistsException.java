package com.foodymoody.be.common.exception;

public class FeedIdNotExistsException extends BusinessException {

    public FeedIdNotExistsException() {
        super("피드 아이디가 없습니다.");
    }
}
