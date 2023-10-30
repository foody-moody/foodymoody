package com.foodymoody.be.common.exception;

public enum ErrorMessage {
    // global
    INVALID_INPUT_VALUE("입력값이 올바르지 않습니다.", "g001"),
    INVALID_ID("유효하지 않은 아이디입니다.", "g002"),
    CREATE_TIME_IS_NULL("생성시간이 null이면 안된다", "g003"),
    // comment
    CONTENT_NOT_EXISTS("댓글이 존재하지 않습니다.", "c001"),
    CONTENT_IS_EMPTY("댓글 내용이 공백입니다.", "c002"),
    CONTENT_IS_SPACE("댓글 내용이 공백입니다.", "c003"),
    FEED_ID_NOT_EXISTS("피드가 존재하지 않습니다.", "c004"),
    CONTENT_IS_OVER_200("댓글은 200자 이하여야 합니다.", "c005"),
    REGISTER_COMMENT_REQUEST_NOT_NULL("등록 요청이 없으면 안된다", "c006"),
    ;

    private final String message;
    private final String code;

    ErrorMessage(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
