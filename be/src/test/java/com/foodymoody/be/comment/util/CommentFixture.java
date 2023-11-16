package com.foodymoody.be.comment.util;

import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import com.foodymoody.be.comment.domain.Comment;
import com.foodymoody.be.comment.domain.CommentId;
import java.time.LocalDateTime;

public class CommentFixture {

    public static final String CONTENT = "content";
    public static final String FEED_ID = "1";
    public static final String COMMENT_ID = "12313113";
    public static final String MEMBER_ID = "32323232";
    public static final String EMPTY_CONTENT = "";
    public static final String CONTENT_OVER_200 = "c".repeat(201);
    public static final String SPACE = " ";
    public static final String NEW_CONTENT = "new content";
    public static final LocalDateTime CREATED_AT = LocalDateTime.of(2021, 1, 1, 1, 1, 1);
    public static final String NOT_EXISTS_ID = "not exists id";
    public static final boolean DELETED = false;
    public static final String NOT_MEMBER_ID = "not member id";

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

    public static RegisterCommentRequest registerCommentRequestWithoutFeedId() {
        RegisterCommentRequest registerCommentRequest = new RegisterCommentRequest();
        registerCommentRequest.setContent(CONTENT);
        return registerCommentRequest;
    }

    public static RegisterCommentRequest registerCommentRequest() {
        RegisterCommentRequest registerCommentRequest = new RegisterCommentRequest();
        registerCommentRequest.setFeedId(FEED_ID);
        registerCommentRequest.setContent(CONTENT);
        return registerCommentRequest;
    }

    public static Comment comment() {
        return new Comment(new CommentId(COMMENT_ID), CONTENT, FEED_ID, DELETED, MEMBER_ID, CREATED_AT);
    }

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
        return new Comment(commentId(), CONTENT, FEED_ID, true, MEMBER_ID, CREATED_AT);
    }
}
