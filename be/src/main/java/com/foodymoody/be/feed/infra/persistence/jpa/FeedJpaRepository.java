package com.foodymoody.be.feed.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.feed.application.usecase.dto.ImageIdNamePair;
import com.foodymoody.be.feed.application.usecase.dto.MenuNameRatingPair;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.entity.ImageMenu;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeedJpaRepository extends JpaRepository<Feed, FeedId> {

    boolean existsAllByIdIn(List<FeedId> feedIds);

    Slice<Feed> findAllByIdIn(List<FeedId> feedIds, Pageable pageable);

    @Query("SELECT NEW com.foodymoody.be.feed.application.usecase.dto.ImageIdNamePair(i.id, i.url)"
            + " FROM ImageMenu im"
            + " JOIN Image i ON im.imageId = i.id"
            + " WHERE im IN :imageMenus")
    Optional<List<ImageIdNamePair>> fetchImageIdUrlList(@Param("imageMenus") List<ImageMenu> imageMenus);

    @Query("SELECT NEW com.foodymoody.be.feed.application.usecase.dto.MenuNameRatingPair(m.name, m.rating)" +
            " FROM ImageMenu im" +
            " JOIN Menu m ON im.menuId = m.id" +
            " WHERE im IN :imageMenus")
    Optional<List<MenuNameRatingPair>> fetchMenuNameRatingList(@Param("imageMenus") List<ImageMenu> imageMenus);

    @Modifying
    @Query("UPDATE Feed f SET f.likeCount = :likeCount WHERE f.id = :feedId")
    void updateLikeCount(@Param("likeCount") int likeCount, @Param("feedId") FeedId feedId);

}
