package com.foodymoody.be.feed_comment.application.dto.data;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import lombok.Getter;

@Getter
public class RegisterFeedReplyData {

    private final FeedCommentId feedCommentId;
    private final MemberId memberId;
    private final Content content;

    public RegisterFeedReplyData(FeedCommentId feedCommentId, MemberId memberId, Content content) {
        this.feedCommentId = feedCommentId;
        this.memberId = memberId;
        this.content = content;
    }

}
