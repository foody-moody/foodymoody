package com.foodymoody.be.feed.application;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
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
