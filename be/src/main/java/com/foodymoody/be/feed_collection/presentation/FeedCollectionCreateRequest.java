package com.foodymoody.be.feed_collection.presentation;

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
    private List<String> feedIds;
    private boolean isPrivate;
}
