package com.foodymoody.be.feed_collection.infra.usecase.dto;

import com.foodymoody.be.common.util.ids.FeedCollectionMoodId;
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
    private List<FeedCollectionMoodId> moodIds;
    private boolean isPrivate;
}
