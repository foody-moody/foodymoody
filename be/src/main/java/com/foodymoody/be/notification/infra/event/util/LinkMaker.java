package com.foodymoody.be.notification.infra.event.util;

import com.foodymoody.be.comment.domain.entity.CommentId;

public class LinkMaker {

    private LinkMaker() {
        throw new IllegalStateException("Utility class");
    }

    public static String makeFeedLink(String id) {
        return String.format("https://foodymoody.site/api/feeds/%s", id);
    }

    public static String makeCommentLink(CommentId id) {
        return String.format("https://foodymoody.site/api/comments/%s", id);
    }
}
