package com.foodymoody.be.feed_comment.persentation;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.data.EditFeedCommentData;
import com.foodymoody.be.feed_comment.application.dto.data.RegisterFeedCommentData;
import com.foodymoody.be.feed_comment.application.dto.data.RegisterFeedReplyData;
import com.foodymoody.be.feed_comment.application.dto.request.EditFeedCommentRequest;
import com.foodymoody.be.feed_comment.application.dto.request.RegisterFeedCommentRequest;
import com.foodymoody.be.feed_comment.application.dto.request.RegisterFeedReplyRequest;

public class FeedCommentTranslator {

    private FeedCommentTranslator() {
        throw new AssertionError(UTILITY_CLASS);
    }

    public static RegisterFeedCommentData toRegisterCommentData(RegisterFeedCommentRequest request, MemberId memberId) {
        var content = new Content(request.getContent());
        var feedId = IdFactory.createFeedId(request.getFeedId());
        return new RegisterFeedCommentData(feedId, content, memberId);
    }

    public static EditFeedCommentData toEditCommentData(
            EditFeedCommentRequest request, FeedCommentId id, MemberId memberId
    ) {
        var content = new Content(request.getContent());
        return new EditFeedCommentData(id, memberId, content);
    }

    public static RegisterFeedReplyData toRegisterReplyData(
            RegisterFeedReplyRequest request, MemberId memberId, FeedCommentId id
    ) {
        var content = new Content(request.getContent());
        return new RegisterFeedReplyData(id, memberId, content);
    }
}
