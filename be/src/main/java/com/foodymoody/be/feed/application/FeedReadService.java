package com.foodymoody.be.feed.application;

import com.foodymoody.be.common.exception.FeedIdNotExistsException;
import com.foodymoody.be.common.exception.ImageNotFoundException;
import com.foodymoody.be.common.exception.MenuNotFoundException;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.entity.ImageMenu;
import com.foodymoody.be.feed.domain.repository.FeedRepository;
import com.foodymoody.be.feed.domain.repository.dto.MemberProfileFeedPreviewResponse;
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

    public void validateId(String feedId) {
        if (!exists(feedId)) {
            throw new FeedIdNotExistsException();
        }
    }

    public void validateIds(List<FeedId> feedIds) {
        if (!feedRepository.existsAllByIdIn(feedIds)) {
            throw new FeedIdNotExistsException();
        }
    }

    private boolean exists(String feedId) {
        return feedRepository.existsById(IdFactory.createFeedId(feedId));
    }

    public Feed findFeed(FeedId id) {
        return feedRepository.findById(id)
                .orElseThrow(FeedIdNotExistsException::new);
    }

    public Slice<Feed> findAll(Pageable pageable) {
        return feedRepository.findAll(pageable);
    }

    public Slice<MemberProfileFeedPreviewResponse> fetchPreviewsByMemberId(MemberId memberId, Pageable pageable) {
        return feedRepository.fetchPreviewsByMemberId(memberId, pageable);
    }

    public List<ImageIdNamePair> fetchImageIdUrlList(List<ImageMenu> imageMenus) {
        return feedRepository.fetchImageIdUrlList(imageMenus)
                .orElseThrow(ImageNotFoundException::new);
    }

    public List<MenuNameRatingPair> fetchMenuNameRatingList(List<ImageMenu> imageMenus) {
        return feedRepository.fetchMenuNameRatingList(imageMenus)
                .orElseThrow(MenuNotFoundException::new);
    }

}
