package com.foodymoody.be.feed_comment.application;

import com.foodymoody.be.common.exception.CommentNotExistsException;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.feed_comment.domain.entity.FeedComment;
import com.foodymoody.be.feed_comment.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentReadService {

    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public FeedComment fetchById(FeedCommentId id) {
        return commentRepository.findById(id)
                .orElseThrow(CommentNotExistsException::new);
    }
}
