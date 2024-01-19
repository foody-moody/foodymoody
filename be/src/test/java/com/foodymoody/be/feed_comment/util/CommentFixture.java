package com.foodymoody.be.feed_comment.util;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.data.RegisterCommentData;
import com.foodymoody.be.feed_comment.domain.entity.FeedComment;
import com.foodymoody.be.feed_comment.domain.entity.FeedReply;
import java.time.LocalDateTime;
import java.util.Random;
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


    public static Content space() {
        return new Content(SPACE);
    }

    public static FeedComment comment() {
        return new FeedComment(new FeedCommentId(COMMENT_ID), content(), feedId(), DELETED, memberId(), CREATED_AT);
    }

    @NotNull
    public static FeedCommentId commentId() {
        return new FeedCommentId(COMMENT_ID);
    }

    public static LocalDateTime newUpdatedAt() {
        return LocalDateTime.of(2021, 1, 2, 3, 4, 5);
    }

    public static FeedCommentId notExistsCommentId() {
        return new FeedCommentId(NOT_EXISTS_ID);
    }

    public static FeedComment deletedComment() {
        return new FeedComment(commentId(), content(), feedId(), true, memberId(), CREATED_AT);
    }

    @NotNull
    public static MemberId memberId() {
        return IdFactory.createMemberId(COMMENT_MEMBER_ID);
    }

    @NotNull
    public static MemberId notExistsMemberId() {
        return IdFactory.createMemberId(NOT_EXISTS_MEMBER_ID);
    }

    public static FeedReply reply() {
        return new FeedReply(
                replyId(),
                content(),
                false,
                replyMemberId(),
                CREATED_AT,
                UPDATED_AT
        );
    }

    public static FeedReplyId notExistsReplyId() {
        return IdFactory.createFeedReplyId(NOT_EXISTS_ID);
    }

    @NotNull
    public static FeedReplyId replyId() {
        return IdFactory.createFeedReplyId();
    }

    public static MemberId replyMemberId() {
        return IdFactory.createMemberId(REPLY_MEMBER_ID);
    }

    @NotNull
    public static Content content() {
        return new Content(CONTENT);
    }

    public static LocalDateTime newLocalTime() {
        return CREATED_AT;
    }

    public static RegisterCommentData registerCommentData() {
        return new RegisterCommentData(feedId(), content(), memberId());
    }

    public static Content newContent() {
        return new Content(NEW_CONTENT);
    }

    public static FeedId feedId() {
        return IdFactory.createFeedId(FEED_ID);
    }

    public static MemberId newMemberId() {
        return IdFactory.createMemberId();
    }

    public static FeedCommentId newCommentId() {
        return IdFactory.createFeedCommentId();
    }

    public static FeedId newFeedId() {
        return IdFactory.createFeedId();
    }

    public static LocalDateTime newCreatedAt() {
        Random random = new Random();
        int day = random.nextInt(31);
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        int second = random.nextInt(60);
        return LocalDateTime.of(2021, 1, day, hour, minute, second);
    }
}
