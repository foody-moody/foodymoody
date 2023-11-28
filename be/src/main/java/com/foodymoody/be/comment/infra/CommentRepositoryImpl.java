package com.foodymoody.be.comment.infra;

import com.foodymoody.be.comment.application.dto.response.CommentResponse;
import com.foodymoody.be.comment.domain.entity.Comment;
import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment.domain.repository.CommentRepository;
import com.foodymoody.be.comment.infra.presistence.CommentJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public Comment save(Comment comment) {
        return commentJpaRepository.save(comment);
    }

    @Override
    public Optional<Comment> findById(CommentId id) {
        return commentJpaRepository.findById(id);
    }

    @Override
    public Slice<CommentResponse> findWithMemberAllByFeedId(String feedId, Pageable pageable) {
        return commentJpaRepository.findWithMemberAllByFeedId(feedId, pageable);
    }
}
