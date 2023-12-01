package com.foodymoody.be.notification.infra.event;

import com.foodymoody.be.member.domain.Member;

public class MessageMaker {

    private MessageMaker() {
        throw new IllegalStateException("Utility class");
    }

    public static String makeCommentAddMessage(Member member) {
        return String.format("%s님이 댓글을 남겼습니다.", member.getNickname());
    }
}
