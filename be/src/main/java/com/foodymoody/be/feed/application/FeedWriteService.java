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
@Transactional
@Service
public class FeedWriteService {

    private final FeedRepository feedRepository;

    public Feed save(Feed feed) {
        return feedRepository.save(feed);
    }

    public void deleteById(FeedId feedId) {
        feedRepository.deleteById(feedId);
    }

}
