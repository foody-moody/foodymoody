package com.foodymoody.be.feed_collection.application.usecase.dto;

import com.foodymoody.be.common.util.ids.FeedCollectionMoodId;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FeedCollectionCreateRequest {

    @NotNull
    private String title;
    private String description;
    private List<FeedCollectionMoodId> moodIds;
    private boolean isPrivate = true;
}
