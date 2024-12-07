package com.foodymoody.be.feed_collection.application.usecase.dto;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionMoodId;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FeedCollectionEditRequest {

    private String title;
    private Content content;
    private String thumbnailUrl;
    private List<FeedCollectionMoodId> moodIds;

}
