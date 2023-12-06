package com.foodymoody.be.comment.infra.persistence;

import com.foodymoody.be.comment.application.dto.response.MemberCommentSummary;
import com.foodymoody.be.comment.domain.entity.Comment;
import com.foodymoody.be.comment.domain.repository.CommentRepository;
import com.foodymoody.be.comment.infra.persistence.jpa.CommentJpaRepository;
import com.foodymoody.be.common.util.ids.CommentId;
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
    public Slice<MemberCommentSummary> findWithMemberAllByFeedId(String feedId, Pageable pageable) {
        return commentJpaRepository.findWithMemberAllByFeedId(feedId, pageable);
    }

    @Override
    public boolean existsById(CommentId commentId) {
        return commentJpaRepository.existsById(commentId);
    }

    @Override
    public Slice<MemberCommentSummary> findWithMemberAllByFeedId(String feedId, String memberId, Pageable pageable) {
        return commentJpaRepository.findWithMemberAllByFeedIdAndMemberId(feedId, memberId, pageable);
    }
}
