package com.foodymoody.be.notification.infra.event.util;

public class MessageMaker {

    private MessageMaker() {
        throw new IllegalStateException("Utility class");
    }

    public static String makeCommentAddMessage(String nickname) {
        return String.format("%s님이 댓글을 남겼습니다.", nickname);
    }

    public static String makeRepliedAddedMessage(String nickname) {
        return String.format("%s님이 답글을 남겼습니다.", nickname);
    }
}
