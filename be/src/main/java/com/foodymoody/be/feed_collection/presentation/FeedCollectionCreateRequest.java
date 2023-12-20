package com.foodymoody.be.feed_collection.presentation;

import com.foodymoody.be.common.util.ids.FeedId;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FeedCollectionCreateRequest {

    private String title;
    private String description;
    private String thumbnailUrl;
    private List<FeedId> feedIds;
    private boolean isPrivate;
}