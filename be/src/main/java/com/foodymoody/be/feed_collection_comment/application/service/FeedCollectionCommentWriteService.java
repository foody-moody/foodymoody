package com.foodymoody.be.feed_collection_comment.application.service;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_comment.application.FeedCollectionCommentMapper;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionComment;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionCommentWriteService {

    private final FeedCollectionCommentRepository repository;
    private final FeedCollectionCommentMapper mapper;

    @Transactional
    public FeedCollectionCommentId post(FeedCollectionId feedCollectionId, Content content, MemberId memberId) {
        var feedCollectionCommentId = IdFactory.createFeedCollectionCommentId();
        var now = LocalDateTime.now();
        var feedCollectionComment = mapper.toEntity(feedCollectionId, content, memberId, feedCollectionCommentId, now);
        return repository.save(feedCollectionComment).getId();
    }

    @Transactional
    public void delete(FeedCollectionCommentId id, MemberId memberId) {
        FeedCollectionComment feedCollectionComment = getFeedCollectionComment(id);
        feedCollectionComment.delete(memberId, LocalDateTime.now());
    }

    @Transactional
    public void edit(FeedCollectionCommentId id, Content content, MemberId memberId) {
        FeedCollectionComment feedCollectionComment = getFeedCollectionComment(id);
        feedCollectionComment.update(content, memberId, LocalDateTime.now());
    }

    @Transactional
    public void addReplyIds(FeedCollectionCommentId commentId, FeedCollectionReplyId id) {
        FeedCollectionComment feedCollectionComment = getFeedCollectionComment(commentId);
        feedCollectionComment.addReplyIds(id);
    }

    private FeedCollectionComment getFeedCollectionComment(FeedCollectionCommentId id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
    }
}
