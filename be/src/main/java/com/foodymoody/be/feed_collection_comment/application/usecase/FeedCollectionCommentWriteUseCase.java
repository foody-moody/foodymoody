package com.foodymoody.be.feed_collection_comment.application.usecase;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.application.service.FeedCollectionWriteService;
import com.foodymoody.be.feed_collection_comment.application.service.FeedCollectionCommentWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionCommentWriteUseCase {

    private final FeedCollectionCommentWriteService feedCollectionCommentWriteService;
    private final FeedCollectionWriteService feedCollectionWriteService;

    @Transactional
    public FeedCollectionCommentId post(FeedCollectionId feedCollectionId, Content content, MemberId memberId) {
        feedCollectionWriteService.checkExistence(feedCollectionId);
        var collectionCommentId = feedCollectionCommentWriteService.post(feedCollectionId, content, memberId);
        feedCollectionWriteService.addCommentId(feedCollectionId, collectionCommentId);
        return collectionCommentId;
    }

    @Transactional
    public void delete(FeedCollectionId feedCollectionId, FeedCollectionCommentId id, MemberId memberId) {
        feedCollectionCommentWriteService.delete(id, memberId);
        feedCollectionWriteService.removeCommentId(feedCollectionId, id);
    }

    @Transactional
    public void edit(FeedCollectionCommentId id, Content content, MemberId memberId) {
        feedCollectionCommentWriteService.edit(id, content, memberId);
    }

}
