package com.foodymoody.be.feed_comment.application.dto.data;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import lombok.Getter;

@Getter
public class RegisterFeedCommentData {

    private final FeedId feedId;
    private final Content content;
    private final MemberId memberId;

    public RegisterFeedCommentData(FeedId feedId, Content content, MemberId memberId) {
        this.feedId = feedId;
        this.content = content;
        this.memberId = memberId;
    }

}
