package com.foodymoody.be.common.exception;

public enum ErrorMessage {
    // global
    INVALID_INPUT_VALUE("입력값이 올바르지 않습니다.", "g001"),
    INVALID_ID("유효하지 않은 아이디입니다.", "g002"),
    CREATE_TIME_IS_NULL("생성시간이 null이면 안된다", "g003"),
    REQUEST_HEADER_NOT_FOUND("요청을 처리하기 위해 필요한 헤더가 존재하지 않습니다", "g004"),
    // comment
    CONTENT_NOT_EXISTS("댓글이 존재하지 않습니다.", "c001"),
    CONTENT_IS_EMPTY("댓글 내용이 공백입니다.", "c002"),
    CONTENT_IS_SPACE("댓글 내용이 공백입니다.", "c003"),
    FEED_ID_NOT_EXISTS("피드가 존재하지 않습니다.", "c004"),
    CONTENT_IS_OVER_200("댓글은 200자 이하여야 합니다.", "c005"),
    REGISTER_COMMENT_REQUEST_NOT_NULL("등록 요청이 없으면 안된다", "c006"),
    COMMENT_NOT_EXISTS("댓글이 존재하지 않는다", "c007"),
    COMMENT_DELETED("삭제된 댓글입니다.", "c008"),
    // member
    MEMBER_NOT_FOUND("존재하지 않는 회원입니다", "m001"),
    DUPLICATE_MEMBER_EMAIL("이미 가입된 이메일입니다", "m002"),
    DUPLICATE_MEMBER_NICKNAME("이미 존재하는 닉네임입니다", "m003"),
    INVALID_CONFIRM_PASSWORD("입력하신 패스워드와 일치하지 않습니다", "m004"),
    // auth
    UNAUTHORIZED("권한이 없습니다", "a001"),
    INVALID_TOKEN("토큰이 유효하지 않습니다", "a002"),
    CLAIM_NOT_FOUND("토큰에 해당 클레임이 존재하지 않습니다", "a003"),
    INVALID_ACCESS_TOKEN("유효하지 않은 액세스 토큰입니다", "a004"),
    MEMBER_INCORRECT_PASSWORD("사용자 정보와 패스워드가 일치하지 않습니다", "a005"),
    // mood
    DUPLICATE_MOOD("이미 존재하는 무드입니다", "o001"),
    // image
    IMAGE_NOT_FOUND("해당 id의 이미지가 존재하지 않습니다", "i001");

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
