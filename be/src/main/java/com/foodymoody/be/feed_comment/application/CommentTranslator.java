package com.foodymoody.be.feed_comment.application;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.data.EditCommentData;
import com.foodymoody.be.feed_comment.application.dto.data.RegisterCommentData;
import com.foodymoody.be.feed_comment.application.dto.data.RegisterReplyData;
import com.foodymoody.be.feed_comment.application.dto.request.EditCommentRequest;
import com.foodymoody.be.feed_comment.application.dto.request.RegisterCommentRequest;
import com.foodymoody.be.feed_comment.application.dto.request.RegisterReplyRequest;
import org.springframework.stereotype.Service;

@Service
public class CommentTranslator {

    public RegisterCommentData toRegisterCommentData(RegisterCommentRequest request, MemberId memberId) {
        var content = new Content(request.getContent());
        var feedId = IdFactory.createFeedId(request.getFeedId());
        return new RegisterCommentData(feedId, content, memberId);
    }

    public EditCommentData toEditCommentData(EditCommentRequest request, FeedCommentId id, MemberId memberId) {
        var content = new Content(request.getContent());
        return new EditCommentData(id, memberId, content);
    }

    public RegisterReplyData toRegisterReplyData(RegisterReplyRequest request, MemberId memberId, FeedCommentId id) {
        var content = new Content(request.getContent());
        return new RegisterReplyData(id, memberId, content);
    }
}
