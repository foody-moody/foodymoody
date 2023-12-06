package com.foodymoody.be.feed.repository;

import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.repository.dto.MemberProfileFeedPreviewResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeedRepository extends JpaRepository<Feed, String> {

    @Query(value =
            "SELECT new com.foodymoody.be.feed.repository.dto.MemberProfileFeedPreviewResponse (f.id, i.url) FROM Feed f "
                    + "INNER JOIN f.imageMenus.imageMenusList im "
                    + "INNER JOIN FETCH Image i ON im.imageId = i.id "
                    + "WHERE f.memberId = :memberId AND im.displayOrder = 0 "
                    + "ORDER BY f.createdAt DESC")
    Slice<MemberProfileFeedPreviewResponse> fetchPreviewsByMemberId(@Param("memberId") String memberId,
            Pageable pageable);
}
