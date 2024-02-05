package com.foodymoody.be.feed.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.feed.application.usecase.dto.ImageIdNamePair;
import com.foodymoody.be.feed.application.usecase.dto.MenuNameRatingPair;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.entity.ImageMenu;
import com.foodymoody.be.feed.domain.repository.FeedRepository;
import com.foodymoody.be.feed.infra.persistence.jpa.FeedJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedRepositoryImpl implements FeedRepository {

    private final FeedJpaRepository feedJpaRepository;

    @Override
    public Feed save(Feed feed) {
        return feedJpaRepository.save(feed);
    }

    @Override
    public void deleteById(FeedId feedId) {
        feedJpaRepository.deleteById(feedId);
    }

    @Override
    public boolean existsAllByIdIn(List<FeedId> feedIds) {
        return feedJpaRepository.existsAllByIdIn(feedIds);
    }

    @Override
    public Slice<Feed> fetchAllByIdIn(List<FeedId> feedIds, Pageable pageable) {
        return feedJpaRepository.findAllByIdIn(feedIds, pageable);
    }

    @Override
    public boolean existsById(FeedId feedId) {
        return feedJpaRepository.existsById(feedId);
    }

    @Override
    public Optional<Feed> fetchById(FeedId id) {
        return feedJpaRepository.findById(id);
    }

    @Override
    public Slice<Feed> fetchAll(Pageable pageable) {
        return feedJpaRepository.findAll(pageable);
    }

    @Override
    public Optional<List<ImageIdNamePair>> fetchImageIdUrlList(List<ImageMenu> imageMenus) {
        return feedJpaRepository.fetchImageIdUrlList(imageMenus);
    }

    @Override
    public Optional<List<MenuNameRatingPair>> fetchMenuNameRatingList(List<ImageMenu> imageMenus) {
        return feedJpaRepository.fetchMenuNameRatingList(imageMenus);
    }

}
