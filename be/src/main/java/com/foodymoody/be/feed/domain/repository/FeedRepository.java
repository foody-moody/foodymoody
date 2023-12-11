package com.foodymoody.be.feed.domain.repository;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.util.List;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.repository.dto.MemberProfileFeedPreviewResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeedRepository extends JpaRepository<Feed, FeedId> {

    @Query(value =
            "SELECT new com.foodymoody.be.feed.domain.repository.dto.MemberProfileFeedPreviewResponse (f.id.value, i.url) FROM Feed f "
                    + "INNER JOIN f.imageMenus.imageMenusList im "
                    + "INNER JOIN FETCH Image i ON im.imageId = i.id "
                    + "WHERE f.memberId = :memberId AND im.displayOrder = 0 "
                    + "ORDER BY f.createdAt DESC")
    Slice<MemberProfileFeedPreviewResponse> fetchPreviewsByMemberId(@Param("memberId") MemberId memberId,
            Pageable pageable);

    boolean existsAllByIdIn(List<FeedId> feedIds);

}
