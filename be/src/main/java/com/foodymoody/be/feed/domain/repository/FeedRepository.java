package com.foodymoody.be.feed.domain.repository;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.usecase.dto.ImageIdNamePair;
import com.foodymoody.be.feed.application.usecase.dto.MenuNameRatingPair;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.entity.ImageMenu;
import com.foodymoody.be.member.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FeedRepository {

    Feed save(Feed feed);

    void deleteById(FeedId feedId);

    boolean existsAllByIdIn(List<FeedId> feedIds);

    Slice<Feed> fetchAllByIdIn(List<FeedId> feedIds, Pageable pageable);

    boolean existsById(FeedId feedId);

    Optional<Feed> fetchById(FeedId id);

    Slice<Feed> fetchAll(Pageable pageable);

    Optional<List<ImageIdNamePair>> fetchImageIdUrlList(List<ImageMenu> imageMenus);

    Optional<List<MenuNameRatingPair>> fetchMenuNameRatingList(List<ImageMenu> imageMenus);

    void updateLikeCount(int heartCount, FeedId feedId);

    void deleteAll(Member member);

}
