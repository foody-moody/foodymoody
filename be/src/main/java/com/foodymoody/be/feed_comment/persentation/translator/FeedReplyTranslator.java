package com.foodymoody.be.feed_comment.persentation.translator;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.data.UpdateFeedReplyData;
import com.foodymoody.be.feed_comment.persentation.dto.UpdateFeedReplyRequest;

public class FeedReplyTranslator {

    private FeedReplyTranslator() {
        throw new AssertionError(UTILITY_CLASS);
    }

    public static UpdateFeedReplyData toUpdateReplyData(
            UpdateFeedReplyRequest request, FeedReplyId feedReplyId, MemberId memberId
    ) {
        return new UpdateFeedReplyData(feedReplyId, memberId, request.getContent());
    }
}
