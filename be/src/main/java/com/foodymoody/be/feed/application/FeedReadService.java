package com.foodymoody.be.feed.application;

import com.foodymoody.be.common.exception.FeedIdNotExistsException;
import com.foodymoody.be.common.exception.ImageNotFoundException;
import com.foodymoody.be.common.exception.MenuNotFoundException;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.entity.ImageMenu;
import com.foodymoody.be.feed.domain.repository.FeedRepository;
import com.foodymoody.be.feed.infra.persistence.jpa.FeedJpaRepository;
import com.foodymoody.be.feed.infra.usecase.dto.ImageIdNamePair;
import com.foodymoody.be.feed.infra.usecase.dto.MenuNameRatingPair;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FeedReadService {

    private final FeedRepository feedRepository;
    private final FeedJpaRepository feedJpaRepository;

    public void validateId(String feedId) {
        if (!isExists(feedId)) {
            throw new FeedIdNotExistsException();
        }
    }

    public void validateIds(List<FeedId> feedIds) {
        if (!isExistsAllByIdIn(feedIds)) {
            throw new FeedIdNotExistsException();
        }
    }

    private boolean isExistsAllByIdIn(List<FeedId> feedIds) {
        return feedRepository.existsAllByIdIn(feedIds);
    }

    private boolean isExists(String feedId) {
        return feedRepository.existsById(IdFactory.createFeedId(feedId));
    }

    public Feed findFeed(FeedId id) {
        return feedRepository.findById(id)
                .orElseThrow(FeedIdNotExistsException::new);
    }

    public Slice<Feed> findAll(Pageable pageable) {
        return feedRepository.findAll(pageable);
    }

    public List<ImageIdNamePair> fetchImageIdUrlList(List<ImageMenu> imageMenus) {
        return feedJpaRepository.fetchImageIdUrlList(imageMenus)
                .orElseThrow(ImageNotFoundException::new);
    }

    public List<MenuNameRatingPair> fetchMenuNameRatingList(List<ImageMenu> imageMenus) {
        return feedJpaRepository.fetchMenuNameRatingList(imageMenus)
                .orElseThrow(MenuNotFoundException::new);
    }

    public Slice<Feed> findAllByIdIn(List<FeedId> feedIds, Pageable pageable) {
        return feedRepository.findAllByIdIn(feedIds, pageable);
    }

}
