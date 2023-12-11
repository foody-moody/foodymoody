package com.foodymoody.be.feed.domain.repository;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.MenuId;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.entity.ImageMenu;
import com.foodymoody.be.feed.domain.repository.dto.MemberProfileFeedPreviewResponse;
import com.foodymoody.be.feed.infra.usecase.dto.ImageIdNamePair;
import com.foodymoody.be.feed.infra.usecase.dto.MenuNameRatingPair;
import java.util.List;
import java.util.Optional;
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

    @Query("SELECT NEW com.foodymoody.be.feed.infra.usecase.dto.ImageIdNamePair(i.id.value, i.url) "
            + "FROM ImageMenu im "
            + "JOIN Image i ON im.imageId = i.id "
            + "WHERE im IN :imageMenus")
    Optional<List<ImageIdNamePair>> fetchImageIdUrlList(@Param("imageMenus") List<ImageMenu> imageMenus);

    @Query("SELECT NEW com.foodymoody.be.feed.infra.usecase.dto.MenuNameRatingPair(m.name, m.rating) " +
            "FROM ImageMenu im " +
            "JOIN Menu m ON im.menuId = m.id " +
            "WHERE im IN :imageMenus")
    Optional<List<MenuNameRatingPair>> fetchMenuNameRatingList(@Param("imageMenus") List<ImageMenu> imageMenus);

}
