package com.foodymoody.be.feed_collection.application;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection.domain.FeedCollectionRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionWriterService {

    private final FeedCollectionRepository repository;

    public void createCollection(
            String title,
            String description,
            String thumbnailUrl,
            boolean isPrivate,
            MemberId memberId,
            List<FeedId> feedIds
    ) {
        var id = IdFactory.createFeedCollectionId();
        LocalDateTime now = LocalDateTime.now();
        var feedCollection = new FeedCollection(
                id, memberId, thumbnailUrl, title, description, 0, isPrivate, false, feedIds
                , now, now
        );
        repository.save(feedCollection);
    }
}
