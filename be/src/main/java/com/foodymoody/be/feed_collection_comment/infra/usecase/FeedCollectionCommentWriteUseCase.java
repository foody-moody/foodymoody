package com.foodymoody.be.feed_collection_comment.infra.usecase;

import com.foodymoody.be.common.util.Content;
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
    public FeedCollectionCommentId post(FeedCollectionId feedCollectionId, Content content, MemberId memberId) {
        feedCollectionWriterService.checkExistence(feedCollectionId);
        var collectionCommentId = feedCollectionCommentWriteService.post(feedCollectionId, content, memberId);
        feedCollectionWriterService.addCommentId(feedCollectionId, collectionCommentId);
        return collectionCommentId;
    }

    @Transactional
    public void delete(FeedCollectionId feedCollectionId, FeedCollectionCommentId id, MemberId memberId) {
        feedCollectionCommentWriteService.delete(id, memberId);
        feedCollectionWriterService.removeCommentId(feedCollectionId, id);
    }

    public void edit(FeedCollectionCommentId id, Content content, MemberId memberId) {
        feedCollectionCommentWriteService.edit(id, content, memberId);
    }
}