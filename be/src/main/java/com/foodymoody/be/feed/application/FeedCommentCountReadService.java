package com.foodymoody.be.feed.application;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.feed_comment.infra.persistence.jpa.CommentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FeedCommentCountReadService {

    private final CommentJpaRepository commentJpaRepository;

    public Long fetchCountByFeedId(FeedId feedId) {
        return commentJpaRepository.fetchCountByFeedId(feedId)
                .orElse(0L);
    }

}
