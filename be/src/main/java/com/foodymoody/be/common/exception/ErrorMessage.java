package com.foodymoody.be.common.exception;

public enum ErrorMessage {
    // global
    INVALID_INPUT_VALUE("입력값이 올바르지 않습니다.", "g001"),
    INVALID_ID(" 유효하지 않은 아이디입니다.", "g002"),
    CREATE_TIME_IS_NULL("생성시간이 null이면 안된다", "g003"),
    REQUEST_HEADER_NOT_FOUND("요청을 처리하기 위해 필요한 헤더가 존재하지 않습니다", "g004"),
    PERMISSION_DENIED_ACCESS("권한이 없습니다.", "g005"),

    COUNT_OUT_OF_BOUNDS("카운트가 더 이상 감소할 수 없습니다", null),
    // comment
    CONTENT_NOT_EXISTS("댓글이 존재하지 않습니다.", "c001"),
    CONTENT_IS_EMPTY("댓글 내용이 공백입니다.", "c002"),
    CONTENT_IS_SPACE("댓글 내용이 공백입니다.", "c003"),
    FEED_ID_NOT_EXISTS("피드가 존재하지 않습니다.", "c004"),
    CONTENT_IS_OVER_200("댓글은 200자 이하여야 합니다.", "c005"),
    REGISTER_COMMENT_REQUEST_NOT_NULL("등록 요청이 없으면 안된다", "c006"),
    COMMENT_NOT_EXISTS("댓글이 존재하지 않는다", "c007"),
    COMMENT_DELETED("삭제된 댓글입니다.", "c008"),
    INVALID_COMMENT_ID("유효하지 않은 댓글 id입니다", "c009"),
    FEED_COMMENT_NOT_FOUND("존재하지 않는 피드 댓글입니다.", "c010"),
    FEED_COMMENT_IS_ALREADY_LIKED("이미 좋아요 누른 피드 댓글입니다.", "c011"),
    FEED_COMMENT_IS_NOT_LIKED("좋아요 기록이 없어 취소할 수 없습니다.", "c012"),
    // reply
    REPLY_NOT_EXISTS("답글이 존재하지 않습니다.", "r001"),
    INVALID_REPLY_ID("유효하지 않은 답글 id입니다", "r002"),
    // member
    MEMBER_NOT_FOUND("존재하지 않는 회원입니다", "m001"),
    DUPLICATE_MEMBER_EMAIL("이미 가입된 이메일입니다", "m002"),
    DUPLICATE_MEMBER_NICKNAME("이미 존재하는 닉네임입니다", "m003"),
    INVALID_CONFIRM_PASSWORD("재입력한 비밀번호가 입력한 비밀번호와 일치하지 않습니다", "m004"),
    PASSWORD_PATTERN_NOT_MATCH("비밀번호는 8글자 이상이어야 합니다", "m005"),
    NOT_FOLLOWING("해당 회원을 팔로우하고있지 않습니다", "m006"),
    ALREADY_FOLLOWING("해당 회원을 이미 팔로우하고 있습니다", "m007"),
    INVALID_MEMBER_ID("유효하지 않은 회원 id입니다", "m008"),
    // auth
    UNAUTHORIZED("권한이 없습니다", "a001"),
    INVALID_TOKEN("토큰이 유효하지 않습니다", "a002"),
    CLAIM_NOT_FOUND("토큰에 해당 클레임이 존재하지 않습니다", "a003"),
    INVALID_ACCESS_TOKEN("유효하지 않은 액세스 토큰입니다", "a004"),
    MEMBER_INCORRECT_PASSWORD("사용자 정보와 비밀번호가 일치하지 않습니다", "a005"),
    // mood
    DUPLICATE_MOOD("이미 존재하는 무드입니다", "o001"),
    MOOD_NOT_FOUND("존재하지 않는 무드입니다", "o002"),
    // feed
    INVALID_FEED_ID("유효하지 않은 피드 id입니다", "f001"),
    // feed notification
    FEED_NOTIFICATION_NOT_FOUND("존재하지 않는 피드 알림입니다.", "fn001"),
    JSON_CONVERT_ERROR("json 변환에 실패했습니다.", "fn002"),
    FEED_COLLECTION_COMMENT_NOT_FOUND("존재하지 않는 피드 컬렉션 댓글입니다.", "fn003"),
    NOTIFICATION_SETTING_NOT_FOUND("존재하지 않는 알림 설정입니다.", "fn004"),

    // feed collection
    FEED_COLLECTION_NOT_FOUND("존재하지 않는 피드 컬렉션입니다.", "fc001"),
    FEED_COLLECTION_REPLY_NOT_FOUND("존재하지 않는 피드 컬렉션 답글입니다.", "fc002"),
    // image
    IMAGE_NOT_FOUND("해당 id의 이미지가 존재하지 않습니다", "i001"),
    IMAGE_UPLOAD_FAILED("이미지 업로드에 실패했습니다", "i002"),
    FILE_SIGNATURE_DOES_NOT_MATCH_CONTENT_TYPE("파일 시그니처와 ContentType 헤더가 일치하지 않습니다", "i003"),
    INVALID_IMAGE_FILE("유효하지 않은 이미지 파일입니다", "i004"),
    UNSUPPORTED_IMAGE_FORMAT_EXCEPTION("지원되지 않는 이미지 형식입니다", "i005"),
    INVALID_IMAGE_URL("유효하지 않은 이미지 url입니다", "i006"),
    MAX_UPLOAD_SIZE_EXCEEDED("2.8MB 이하의 이미지만 업로드 가능합니다", "i007"),
    INVALID_IMAGE_ID("유효하지 않은 이미지 id입니다", "i008"),
    MENU_NOT_FOUND("유효하지 않은 메뉴 id입니다.", "i010"),
    IS_LIKED_NOT_EXISTS("isLiked를 찾아올 수 없습니다.", "i012"),
    FEED_HEART_ALREADY_EXISTS("이미 좋아요 누른 피드입니다.", "i014"),
    FEED_HEART_NOT_FOUND("좋아요 기록이 없어 취소할 수 없습니다.", "i016"),
    // store
    STORE_NOT_FOUND("해당 id의 식당이 존재하지 않습니다", "s001"),
    ALREADY_LIKED_STORE("이미 좋아요를 누른 식당입니다", "s002"),
    NOT_LIKED_STORE("좋아요를 누르지 않은 식당입니다", "s003");

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
