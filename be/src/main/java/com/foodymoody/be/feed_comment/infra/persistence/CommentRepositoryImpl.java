package com.foodymoody.be.feed_comment.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.response.MemberCommentSummary;
import com.foodymoody.be.feed_comment.domain.entity.FeedComment;
import com.foodymoody.be.feed_comment.domain.repository.CommentRepository;
import com.foodymoody.be.feed_comment.infra.persistence.jpa.CommentJpaRepository;
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
    public FeedComment save(FeedComment feedComment) {
        return commentJpaRepository.save(feedComment);
    }

    @Override
    public Optional<FeedComment> findById(FeedCommentId id) {
        return commentJpaRepository.findById(id);
    }

    @Override
    public Slice<MemberCommentSummary> findWithMemberAllByFeedId(FeedId feedId, Pageable pageable) {
        return commentJpaRepository.findWithMemberAllByFeedId(feedId, pageable);
    }

    @Override
    public Slice<MemberCommentSummary> findWithMemberAllByFeedId(FeedId feedId, MemberId memberId, Pageable pageable) {
        return commentJpaRepository.findWithMemberAllByFeedIdAndMemberId(feedId, memberId, pageable);
    }
}
