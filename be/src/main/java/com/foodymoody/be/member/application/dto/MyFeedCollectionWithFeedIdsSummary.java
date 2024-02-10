package com.foodymoody.be.member.application.dto;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedId;
import java.util.List;
import lombok.Getter;

@Getter
public class MyFeedCollectionWithFeedIdsSummary {

    private FeedCollectionId id;
    private String title;
    private List<FeedId> feedIds;

    public MyFeedCollectionWithFeedIdsSummary(FeedCollectionId id, String title, List<FeedId> feedIds) {
        this.id = id;
        this.title = title;
        this.feedIds = feedIds;
    }
}
