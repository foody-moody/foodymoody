package com.foodymoody.be.feed_collection_reply_like_count.application;

import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.feed_collection_reply_like_count.domain.FeedCollectionReplyLikeCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionReplyLikeCountReadService {

    private final FeedCollectionReplyLikeCountRepository repository;

    @Transactional(readOnly = true)
    public long getCountByFeedCollectionReplyId(FeedCollectionReplyId feedCollectionReplyId) {
        var feedCollectionReplyLikeCount = repository.findByFeedCollectionReplyId(feedCollectionReplyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피드 컬렉션 댓글 카운터입니다."));
        return feedCollectionReplyLikeCount.getLikeCount();
    }
}
