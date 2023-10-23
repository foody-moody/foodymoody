package com.foodymoody.be.comment.service;

import com.foodymoody.be.common.exception.BusinessException;

public class ContentNotExistsException extends BusinessException {

    public ContentNotExistsException() {
        super("댓글 내용이 없습니다.");
    }
}
