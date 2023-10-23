package com.foodymoody.be.common.exception;

public class ContentIsSpaceException extends BusinessException {

    public ContentIsSpaceException() {
        super("댓글 내용이 space입니다.");
    }
}
