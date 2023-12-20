package com.foodymoody.be.feed_collection_comment.application;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionCommentReadService {

    private final FeedCollectionCommentRepository repository;

    public void validateExistence(FeedCollectionCommentId commentId) {
        if (repository.existsById(commentId)) {
            return;
        }
        throw new IllegalArgumentException("존재하지 않는 댓글입니다.");
    }
}
