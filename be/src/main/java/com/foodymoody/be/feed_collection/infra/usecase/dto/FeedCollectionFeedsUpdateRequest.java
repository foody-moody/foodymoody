package com.foodymoody.be.feed_collection.infra.usecase.dto;

import com.foodymoody.be.common.util.ids.FeedId;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedCollectionFeedsUpdateRequest {

    private List<FeedId> feedIds;
}
