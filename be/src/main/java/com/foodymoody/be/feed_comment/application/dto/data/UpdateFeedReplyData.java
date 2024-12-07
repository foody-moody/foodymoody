package com.foodymoody.be.feed_comment.application.dto.data;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import lombok.Getter;

@Getter
public class UpdateFeedReplyData {

    private final FeedReplyId feedReplyId;
    private final MemberId memberId;
    private final Content content;

    public UpdateFeedReplyData(FeedReplyId feedReplyId, MemberId memberId, String content) {
        this.feedReplyId = feedReplyId;
        this.memberId = memberId;
        this.content = new Content(content);
    }

}
