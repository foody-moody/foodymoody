package com.foodymoody.be.comment.application;

import com.foodymoody.be.comment.domain.entity.Comment;
import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment.domain.repository.CommentRepository;
import com.foodymoody.be.common.exception.CommentNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentReadService {

    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public Comment fetchById(CommentId id) {
        return commentRepository.findById(id).orElseThrow(CommentNotExistsException::new);
    }

    @Transactional(readOnly = true)
    public void validate(CommentId commentId) {
        if (commentRepository.existsById(commentId)) {
            return;
        }
        throw new CommentNotExistsException();
    }
}
