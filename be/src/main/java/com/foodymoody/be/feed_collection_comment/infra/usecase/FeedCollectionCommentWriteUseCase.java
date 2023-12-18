package com.foodymoody.be.feed_collection_comment.infra.usecase;

import com.foodymoody.be.common.util.CommentContent;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.application.FeedCollectionWriterService;
import com.foodymoody.be.feed_collection_comment.application.FeedCollectionCommentWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionCommentWriteUseCase {

    private final FeedCollectionCommentWriteService feedCollectionCommentWriteService;
    private final FeedCollectionWriterService feedCollectionWriterService;

    @Transactional
    public void post(FeedCollectionId feedCollectionId, CommentContent content, MemberId memberId) {
        feedCollectionWriterService.checkExistence(feedCollectionId);
        FeedCollectionCommentId collectionCommentId = feedCollectionCommentWriteService.post(
                feedCollectionId, content, memberId);
        feedCollectionWriterService.addCommentId(feedCollectionId, collectionCommentId);
    }
}
