package com.foodymoody.be.common.exception;

public class RegisterCommentRequestNotNullException extends BusinessException {

    public RegisterCommentRequestNotNullException() {
        super("댓글 등록 요청이 null입니다.");
    }
}
