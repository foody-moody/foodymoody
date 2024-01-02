package com.foodymoody.be.feed.domain.repository;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.feed.domain.entity.Feed;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, FeedId> {

    boolean existsAllByIdIn(List<FeedId> feedIds);

    Slice<Feed> findAllByIdIn(List<FeedId> feedIds, Pageable pageable);
}
