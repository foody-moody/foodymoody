package com.foodymoody.be.notification.infra.event.dto;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.notification.domain.NotificationDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FeedCollectionNotificationDetails extends NotificationDetails {

    private FeedCollectionId feedCollectionId;
    private String feedCollectionTitle;
    private String feedCollectionDescription;

    public FeedCollectionNotificationDetails(
            FeedCollectionId feedCollectionId,
            String feedCollectionTitle,
            String feedCollectionDescription
    ) {
        this.feedCollectionId = feedCollectionId;
        this.feedCollectionTitle = feedCollectionTitle;
        this.feedCollectionDescription = feedCollectionDescription;
    }
}
