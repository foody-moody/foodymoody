package com.foodymoody.be.notification.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.foodymoody.be.notification.infra.event.dto.FeedCollectionCommentLikeNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCollectionCommentNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCollectionLikeNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCollectionNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCollectionReplyLikeNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCollectionReplyNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCommentLikeNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCommentNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCommentReplyLikeNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCommentReplyNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedLikeNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.MemberFollowedNotificationDetails;

@JsonTypeInfo(use = Id.NAME)
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = FeedCollectionCommentLikeNotificationDetails.class),
                @JsonSubTypes.Type(value = FeedCollectionCommentNotificationDetails.class),
                @JsonSubTypes.Type(value = FeedCollectionLikeNotificationDetails.class),
                @JsonSubTypes.Type(value = FeedCollectionNotificationDetails.class),
                @JsonSubTypes.Type(value = FeedCollectionReplyLikeNotificationDetails.class),
                @JsonSubTypes.Type(value = FeedCollectionReplyNotificationDetails.class),
                @JsonSubTypes.Type(value = FeedCommentLikeNotificationDetails.class),
                @JsonSubTypes.Type(value = FeedCommentNotificationDetails.class),
                @JsonSubTypes.Type(value = FeedCommentReplyLikeNotificationDetails.class),
                @JsonSubTypes.Type(value = FeedCommentReplyNotificationDetails.class),
                @JsonSubTypes.Type(value = FeedLikeNotificationDetails.class),
                @JsonSubTypes.Type(value = FeedNotificationDetails.class),
                @JsonSubTypes.Type(value = MemberFollowedNotificationDetails.class),
        }
)
public class NotificationDetails {

}
