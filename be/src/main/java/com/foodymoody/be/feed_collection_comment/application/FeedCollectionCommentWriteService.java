package com.foodymoody.be.feed_collection_comment.application;

import com.foodymoody.be.common.util.CommentContent;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionComment;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionCommentWriteService {

    private final FeedCollectionCommentRepository feedCollectionCommentRepository;

    @Transactional
    public FeedCollectionCommentId post(FeedCollectionId feedCollectionId, CommentContent content, MemberId memberId) {
        FeedCollectionCommentId feedCollectionCommentId = IdFactory.createFeedCollectionCommentId();
        LocalDateTime now = LocalDateTime.now();
        FeedCollectionComment feedCollectionComment = new FeedCollectionComment(
                feedCollectionCommentId, feedCollectionId, memberId, content, now);
        return feedCollectionCommentRepository.save(feedCollectionComment).getId();
    }
}
