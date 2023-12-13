package com.foodymoody.be.notification.application;

import static com.foodymoody.be.common.event.NotificationType.COLLECTION_COMMENT_ADDED_EVENT;
import static com.foodymoody.be.common.event.NotificationType.COMMENT_LIKED_ADDED_EVENT;
import static com.foodymoody.be.common.event.NotificationType.FEED_COLLECTION_LIKED_ADDED_EVENT;
import static com.foodymoody.be.common.event.NotificationType.FEED_COMMENT_ADDED_EVENT;
import static com.foodymoody.be.common.event.NotificationType.FEED_LIKED_ADDED_EVENT;
import static com.foodymoody.be.common.event.NotificationType.MEMBER_FOLLOWED_EVENT;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification.domain.FeedNotification;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class NotificationSpecs {

    private NotificationSpecs() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<FeedNotification> searchByType(
            boolean isFeedComment, boolean isCollectionComment, boolean isFeedLike, boolean isCollectionLike,
            boolean isReplyLike, boolean isFollow
    ) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (isFeedComment) {
                predicates.add(builder.equal(root.get("type"), FEED_COMMENT_ADDED_EVENT));
            }
            if (isCollectionComment) {
                predicates.add(builder.equal(root.get("type"), COLLECTION_COMMENT_ADDED_EVENT));
            }
            if (isFeedLike) {
                predicates.add(builder.equal(root.get("type"), FEED_LIKED_ADDED_EVENT));
            }
            if (isReplyLike) {
                predicates.add(builder.equal(root.get("type"), COMMENT_LIKED_ADDED_EVENT));
            }
            if (isCollectionLike) {
                predicates.add(builder.equal(root.get("type"), FEED_COLLECTION_LIKED_ADDED_EVENT));
            }
            if (isFollow) {
                predicates.add(builder.equal(root.get("type"), MEMBER_FOLLOWED_EVENT));
            }
            return builder.or(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<FeedNotification> isToMemberSpec(MemberId memberId) {
        return (root, query, builder) -> builder.equal(root.get("toMemberId"), memberId);
    }

    public static Specification<FeedNotification> isDeletedSpec(boolean isDeleted) {
        return (root, query, builder) -> builder.equal(root.get("isDeleted"), isDeleted);
    }

    public static Specification<FeedNotification> isReadSpec(boolean isRead) {
        return (root, query, builder) -> builder.equal(root.get("isRead"), isRead);
    }
}
