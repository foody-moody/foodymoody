package com.foodymoody.be.notification.infra.event.dto;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.notification.domain.NotificationDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FeedCollectionLikeNotificationDetails extends NotificationDetails {

    private FeedCollectionId feedCollectionId;
    private String feedCollectionThumbnailUrl;

    public FeedCollectionLikeNotificationDetails(
            FeedCollectionId feedCollectionId,
            String feedCollectionThumbnailUrl
    ) {
        this.feedCollectionId = feedCollectionId;
        this.feedCollectionThumbnailUrl = feedCollectionThumbnailUrl;
    }

}
