package com.foodymoody.be.notification.infra.event.dto;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.notification.domain.NotificationDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FeedNotificationDetails extends NotificationDetails {

    private FeedId feedId;
    private String feedThumbnail;

    public FeedNotificationDetails(
            FeedId feedId,
            String feedThumbnail
    ) {
        this.feedId = feedId;
        this.feedThumbnail = feedThumbnail;
    }
}
