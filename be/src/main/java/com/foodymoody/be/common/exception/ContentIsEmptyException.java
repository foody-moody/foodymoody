package com.foodymoody.be.common.exception;

public class ContentIsEmptyException extends BusinessException {

    public ContentIsEmptyException() {
        super("댓글 내용이 공백입니다.");
    }
}
