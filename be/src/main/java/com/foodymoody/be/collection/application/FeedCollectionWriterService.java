package com.foodymoody.be.collection.application;

import com.foodymoody.be.collection.domain.FeedCollection;
import com.foodymoody.be.collection.domain.FeedCollectionRepository;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionWriterService {

    private final FeedCollectionRepository repository;

    public void createCollection(String title, String description, String thumbnailUrl, boolean isPrivate,
            MemberId memberId,
            List<FeedId> feedIds) {
        FeedCollectionId id = IdFactory.createFeedCollectionId();
        FeedCollection feedCollection = new FeedCollection(id, memberId, thumbnailUrl, title, description, isPrivate,
                false, feedIds);
        repository.save(feedCollection);
    }
}
