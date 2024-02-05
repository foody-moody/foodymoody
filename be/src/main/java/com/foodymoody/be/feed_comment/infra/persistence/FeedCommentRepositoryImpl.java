package com.foodymoody.be.feed_comment.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.domain.entity.FeedComment;
import com.foodymoody.be.feed_comment.domain.entity.MemberFeedCommentSummary;
import com.foodymoody.be.feed_comment.domain.repository.FeedCommentRepository;
import com.foodymoody.be.feed_comment.infra.persistence.jpa.FeedCommentJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCommentRepositoryImpl implements FeedCommentRepository {

    private final FeedCommentJpaRepository feedCommentJpaRepository;

    @Override
    public FeedComment save(FeedComment feedComment) {
        return feedCommentJpaRepository.save(feedComment);
    }

    @Override
    public Optional<FeedComment> findById(FeedCommentId id) {
        return feedCommentJpaRepository.findById(id);
    }

    @Override
    public Slice<MemberFeedCommentSummary> findWithMemberAllByFeedId(FeedId feedId, Pageable pageable) {
        return feedCommentJpaRepository.findWithMemberAllByFeedId(feedId, pageable);
    }

    @Override
    public Slice<MemberFeedCommentSummary> findWithMemberAllByFeedId(
            FeedId feedId, MemberId memberId, Pageable pageable
    ) {
        return feedCommentJpaRepository.findWithMemberAllByFeedIdAndMemberId(feedId, memberId, pageable);
    }
}
