package com.foodymoody.be.common.exception;

public class ContentNotExistsException extends BusinessException {

    public ContentNotExistsException() {
        super("댓글 내용이 없습니다.");
    }
}
