package com.foodymoody.be.feed_comment_like.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedCommentLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment_like.domain.FeedCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentHeartJpaRepository extends JpaRepository<FeedCommentLike, FeedCommentLikeId> {

    void deleteByFeedCommentIdAndMemberId(FeedCommentId feedCommentId, MemberId memberId);

    boolean existsByFeedCommentIdAndMemberId(FeedCommentId feedCommentId, MemberId memberId);
}
