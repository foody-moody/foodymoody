package com.foodymoody.be.feed_like.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.FeedLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_like.domain.entity.FeedLike;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeedLikeJpaRepository extends JpaRepository<FeedLike, FeedLikeId> {

    boolean existsByMemberIdAndFeedId(MemberId memberId, FeedId feedId);

    void deleteByFeedIdAndMemberId(FeedId feedId, MemberId memberId);

    @Query("SELECT fh.isLiked "
            + "FROM FeedLike fh "
            + "WHERE fh.feedId = :feedId "
            + "AND fh.memberId = :memberId")
    Optional<Boolean> fetchIsLiked(@Param("feedId") FeedId feedId, @Param("memberId") MemberId memberId);

}
