package com.foodymoody.be.common.exception;

public class ContentIsOver200Exception extends BusinessException {

    public ContentIsOver200Exception() {
        super("댓글 내용이 200자를 넘습니다.");
    }
}
