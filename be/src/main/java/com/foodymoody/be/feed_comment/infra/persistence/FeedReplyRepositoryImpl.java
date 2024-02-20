package com.foodymoody.be.feed_comment.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.domain.entity.FeedReply;
import com.foodymoody.be.feed_comment.domain.entity.MemberFeedReplySummary;
import com.foodymoody.be.feed_comment.domain.repository.FeedReplyRepository;
import com.foodymoody.be.feed_comment.infra.persistence.jpa.FeedReplyJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedReplyRepositoryImpl implements FeedReplyRepository {

    private final FeedReplyJpaRepository feedReplyJpaRepository;

    @Override
    public Slice<MemberFeedReplySummary> findByCommentId(FeedCommentId feedCommentId, Pageable pageable) {
        return feedReplyJpaRepository.findReplyByCommentId(feedCommentId, pageable);
    }

    @Override
    public Slice<MemberFeedReplySummary> findByCommentIdAndMemberId(
            FeedCommentId feedCommentId,
            MemberId memberId,
            Pageable pageable
    ) {
        return feedReplyJpaRepository.findReplyByCommentIdAndMemberId(feedCommentId, memberId, pageable);
    }

    @Override
    public boolean existsById(FeedReplyId feedReplyId) {
        return feedReplyJpaRepository.existsById(feedReplyId);
    }

    @Override
    public Optional<FeedReply> findById(FeedReplyId feedReplyId) {
        return feedReplyJpaRepository.findByIdAndDeleted(feedReplyId, false);
    }
}
