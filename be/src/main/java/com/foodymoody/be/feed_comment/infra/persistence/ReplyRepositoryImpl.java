package com.foodymoody.be.feed_comment.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.response.MemberReplySummary;
import com.foodymoody.be.feed_comment.domain.entity.FeedReply;
import com.foodymoody.be.feed_comment.domain.repository.ReplyRepository;
import com.foodymoody.be.feed_comment.infra.persistence.jpa.ReplyJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReplyRepositoryImpl implements ReplyRepository {

    private final ReplyJpaRepository replyJpaRepository;

    @Override
    public Slice<MemberReplySummary> findByCommentId(FeedCommentId feedCommentId, Pageable pageable) {
        return replyJpaRepository.findReplyByCommentId(feedCommentId, pageable);
    }

    @Override
    public Slice<MemberReplySummary> findByCommentIdAndMemberId(
            FeedCommentId feedCommentId, MemberId memberId,
            Pageable pageable
    ) {
        return replyJpaRepository.findReplyByCommentIdAndMemberId(feedCommentId, memberId, pageable);
    }

    @Override
    public boolean existsById(FeedReplyId feedReplyId) {
        return replyJpaRepository.existsById(feedReplyId);
    }

    @Override
    public Optional<FeedReply> findById(FeedReplyId feedReplyId) {
        return replyJpaRepository.findById(feedReplyId);
    }
}
