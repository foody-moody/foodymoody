package com.foodymoody.be.member.application.dto.response;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionMoodId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyFeedCollectionMoodSummary {

    private FeedCollectionMoodId id;
    private String name;
    private FeedCollectionId feedCollectionId;

}
