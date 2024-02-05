package com.foodymoody.be.feed_collection_comment_like_count.application;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.feed_collection_comment_like_count.domain.FeedCollectionCommentLikeCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionCommentLikeCountReadService {

    private final FeedCollectionCommentLikeCountRepository repository;

    @Transactional(readOnly = true)
    public long getCountByFeedCollectionCommentId(FeedCollectionCommentId feedCollectionCommentId) {
        var feedCollectionCommentLikeCount = repository.findByFeedCollectionCommentId(feedCollectionCommentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피드 컬렉션 댓글 카운터입니다."));
        return feedCollectionCommentLikeCount.getLikeCount();
    }

}
