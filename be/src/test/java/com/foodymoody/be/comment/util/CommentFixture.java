package com.foodymoody.be.comment.util;

import com.foodymoody.be.comment.application.dto.request.RegisterCommentRequest;
import com.foodymoody.be.comment.domain.entity.Comment;
import com.foodymoody.be.comment.domain.entity.Reply;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.ReplyId;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class CommentFixture {

    public static final String CONTENT = "content";
    public static final String FEED_ID = "1";
    public static final String COMMENT_ID = "12313113";
    public static final String COMMENT_MEMBER_ID = "32323232";
    public static final String REPLY_MEMBER_ID = "reply member id";
    public static final String EMPTY_CONTENT = "";
    public static final String CONTENT_OVER_200 = "c".repeat(201);
    public static final String SPACE = " ";
    public static final String NEW_CONTENT = "new content";
    public static final LocalDateTime CREATED_AT = LocalDateTime.of(2021, 1, 1, 1, 1, 1);
    public static final LocalDateTime UPDATED_AT = LocalDateTime.of(2021, 1, 2, 3, 4, 5);
    public static final String NOT_EXISTS_ID = "not exists id";
    public static final boolean DELETED = false;
    public static final String NOT_EXISTS_MEMBER_ID = "not member id";

    public static RegisterCommentRequest registerCommentRequestWithoutContent() {
        RegisterCommentRequest registerCommentRequest = new RegisterCommentRequest();
        registerCommentRequest.setFeedId(FEED_ID);
        return registerCommentRequest;
    }

    public static RegisterCommentRequest registerCommentRequestWithEmptyContent() {
        RegisterCommentRequest registerCommentRequest = new RegisterCommentRequest();
        registerCommentRequest.setFeedId(FEED_ID);
        registerCommentRequest.setContent(EMPTY_CONTENT);
        return registerCommentRequest;
    }

    public static RegisterCommentRequest registerCommentRequestWithSpace() {
        RegisterCommentRequest registerCommentRequest = new RegisterCommentRequest();
        registerCommentRequest.setFeedId(FEED_ID);
        registerCommentRequest.setContent(SPACE);
        return registerCommentRequest;
    }

    public static RegisterCommentRequest registerCommentRequestWithContentOver200() {
        RegisterCommentRequest registerCommentRequest = new RegisterCommentRequest();
        registerCommentRequest.setFeedId(FEED_ID);
        registerCommentRequest.setContent(CONTENT_OVER_200);
        return registerCommentRequest;
    }

    public static RegisterCommentRequest registerCommentRequest() {
        RegisterCommentRequest registerCommentRequest = new RegisterCommentRequest();
        registerCommentRequest.setFeedId(FEED_ID);
        registerCommentRequest.setContent(CONTENT);
        return registerCommentRequest;
    }

    public static Comment comment() {
        return new Comment(new CommentId(COMMENT_ID), CONTENT, feedId(), DELETED, memberId(), CREATED_AT);
    }

    @NotNull
    public static CommentId commentId() {
        return new CommentId(COMMENT_ID);
    }

    public static LocalDateTime newUpdatedAt() {
        return LocalDateTime.of(2021, 1, 2, 3, 4, 5);
    }

    public static CommentId notExistsCommentId() {
        return new CommentId(NOT_EXISTS_ID);
    }

    public static Comment deletedComment() {
        return new Comment(commentId(), CONTENT, feedId(), true, memberId(), CREATED_AT);
    }

    @NotNull
    public static MemberId memberId() {
        return IdFactory.createMemberId(COMMENT_MEMBER_ID);
    }

    @NotNull
    public static MemberId notExistsMemberId() {
        return IdFactory.createMemberId(NOT_EXISTS_MEMBER_ID);
    }

    public static Reply reply() {
        return new Reply(
                replyId(),
                content(),
                false,
                replyMemberId(),
                CREATED_AT,
                UPDATED_AT
        );
    }

    public static ReplyId notExistsReplyId() {
        return IdFactory.createReplyId(NOT_EXISTS_ID);
    }

    @NotNull
    public static ReplyId replyId() {
        return IdFactory.createReplyId("reply id");
    }

    public static MemberId replyMemberId() {
        return IdFactory.createMemberId(REPLY_MEMBER_ID);
    }

    @NotNull
    public static String content() {
        return "reply content";
    }

    static FeedId feedId() {
        return IdFactory.createFeedId(FEED_ID);
    }
}
