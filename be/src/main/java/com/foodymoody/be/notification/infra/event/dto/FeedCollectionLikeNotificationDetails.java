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
    private String feedCollectionTitle;
    private String feedCollectionDescription;

    public FeedCollectionLikeNotificationDetails(
            FeedCollectionId feedCollectionId,
            String feedCollectionThumbnailUrl,
            String feedCollectionTitle,
            String feedCollectionDescription
    ) {
        this.feedCollectionId = feedCollectionId;
        this.feedCollectionThumbnailUrl = feedCollectionThumbnailUrl;
        this.feedCollectionTitle = feedCollectionTitle;
        this.feedCollectionDescription = feedCollectionDescription;
    }
}
