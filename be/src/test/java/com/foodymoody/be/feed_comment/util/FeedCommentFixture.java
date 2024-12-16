package com.foodymoody.be.feed_comment.util;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.data.RegisterFeedCommentData;
import com.foodymoody.be.feed_comment.application.dto.data.RegisterFeedReplyData;
import com.foodymoody.be.feed_comment.domain.entity.FeedComment;
import com.foodymoody.be.feed_comment.domain.entity.FeedReply;
import com.foodymoody.be.feed_comment.domain.entity.MemberFeedCommentSummary;
import com.foodymoody.be.feed_comment.domain.entity.MemberFeedReplySummary;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class FeedCommentFixture {

    public static final String CONTENT = "content";
    public static final String FEED_ID = "1";
    public static final String COMMENT_ID = "12313113";
    public static final String COMMENT_MEMBER_ID = "32323232";
    public static final String REPLY_MEMBER_ID = "reply member id";
    public static final String SPACE = " ";
    public static final String NEW_CONTENT = "new content";
    public static final LocalDateTime CREATED_AT = LocalDateTime.of(2021, 1, 1, 1, 1, 1);
    public static final LocalDateTime UPDATED_AT = LocalDateTime.of(2021, 1, 2, 3, 4, 5);
    public static final String NOT_EXISTS_ID = "not exists id";
    public static final boolean DELETED = false;
    public static final String NOT_EXISTS_MEMBER_ID = "not member id";
    public static final String NICKNAME = "nickname";
    public static final String IMAGE_URL = "image url";
    public static final FeedReplyId FEED_REPLY_ID = IdFactory.createFeedReplyId();

    public static Content space() {
        return new Content(SPACE);
    }

    public static FeedComment comment() {
        return new FeedComment(new FeedCommentId(COMMENT_ID), content(), feedId(), DELETED, memberId(), CREATED_AT);
    }

    @NotNull
    public static FeedCommentId feedCommentId() {
        return new FeedCommentId(COMMENT_ID);
    }

    public static LocalDateTime newUpdatedAt() {
        return LocalDateTime.of(2021, 1, 2, 3, 4, 5);
    }

    public static FeedComment deletedComment() {
        return new FeedComment(feedCommentId(), content(), feedId(), true, memberId(), CREATED_AT);
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
                feedReplyId(),
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
    public static FeedReplyId feedReplyId() {
        return FEED_REPLY_ID;
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

    public static RegisterFeedCommentData registerCommentData() {
        return new RegisterFeedCommentData(feedId(), content(), memberId());
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

    public static String nickname() {
        return NICKNAME;
    }

    public static String imageUrl() {
        return IMAGE_URL;
    }

    public static MemberFeedCommentSummary memberFeedCommentSummary() {
        return new MemberFeedCommentSummary() {
            @Override
            public FeedCommentId getId() {
                return feedCommentId();
            }

            @Override
            public Content getContent() {
                return content();
            }

            @Override
            public LocalDateTime getCreatedAt() {
                return CREATED_AT;
            }

            @Override
            public LocalDateTime getUpdatedAt() {
                return UPDATED_AT;
            }

            @Override
            public MemberId getMemberId() {
                return memberId();
            }

            @Override
            public String getNickname() {
                return nickname();
            }

            @Override
            public String getImageUrl() {
                return imageUrl();
            }

            @Override
            public boolean isHasReply() {
                return false;
            }

            @Override
            public long getReplyCount() {
                return 0;
            }

            @Override
            public long getLikeCount() {
                return 0;
            }

            @Override
            public boolean isLiked() {
                return false;
            }
        };
    }

    public static LocalDateTime createdAt() {
        return CREATED_AT;
    }

    public static RegisterFeedCommentData registerFeedCommentData() {
        return new RegisterFeedCommentData(feedId(), content(), memberId());
    }

    public static RegisterFeedReplyData registerFeedReplyData() {
        return new RegisterFeedReplyData(feedCommentId(), memberId(), content());
    }

    public static MemberFeedReplySummary memberFeedReplySummary() {
        return new MemberFeedReplySummary() {
            @Override
            public FeedReplyId getReplyId() {
                return feedReplyId();
            }

            @Override
            public Content getContent() {
                return content();
            }

            @Override
            public MemberId getMemberId() {
                return memberId();
            }

            @Override
            public String getNickname() {
                return nickname();
            }

            @Override
            public String getImageUrl() {
                return imageUrl();
            }

            @Override
            public long getLikeCount() {
                return 0;
            }

            @Override
            public boolean isLiked() {
                return false;
            }

            @Override
            public LocalDateTime getCreatedAt() {
                return createdAt();
            }

            @Override
            public LocalDateTime getUpdatedAt() {
                return createdAt();
            }
        };
    }

}
