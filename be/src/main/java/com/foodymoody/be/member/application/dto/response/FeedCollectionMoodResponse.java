package com.foodymoody.be.member.application.dto.response;

import com.foodymoody.be.common.util.ids.FeedCollectionMoodId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FeedCollectionMoodResponse {

    private FeedCollectionMoodId id;
    private String name;

}
