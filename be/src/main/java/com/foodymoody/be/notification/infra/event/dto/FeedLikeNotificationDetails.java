package com.foodymoody.be.notification.infra.event.dto;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.notification.domain.NotificationDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FeedLikeNotificationDetails extends NotificationDetails {

    private FeedId feedId;
    private String feedThumbnailUrl;

    public FeedLikeNotificationDetails(FeedId feedId, String feedThumbnailUrl) {
        this.feedId = feedId;
        this.feedThumbnailUrl = feedThumbnailUrl;
    }

}
