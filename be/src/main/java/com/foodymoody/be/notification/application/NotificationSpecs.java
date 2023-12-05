package com.foodymoody.be.notification.application;

import static com.foodymoody.be.common.event.NotificationType.COMMENT_ADDED;
import static com.foodymoody.be.common.event.NotificationType.HEART_ADDED;
import static com.foodymoody.be.common.event.NotificationType.REPLY_ADDED;

import com.foodymoody.be.notification.domain.FeedNotification;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class NotificationSpecs {

    private NotificationSpecs() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<FeedNotification> searchByType(boolean isComment, boolean isHeart, boolean isFeed) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (isComment) {
                predicates.add(builder.equal(root.get("type"), REPLY_ADDED));
            }
            if (isHeart) {
                predicates.add(builder.equal(root.get("type"), HEART_ADDED));
            }
            if (isFeed) {
                predicates.add(builder.equal(root.get("type"), COMMENT_ADDED));
            }
            return builder.or(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<FeedNotification> isToMemberSpec(String memberId) {
        return (root, query, builder) -> builder.equal(root.get("toMemberId"), memberId);
    }

    public static Specification<FeedNotification> isDeletedSpec(boolean isDeleted) {
        return (root, query, builder) -> builder.equal(root.get("isDeleted"), isDeleted);
    }

    public static Specification<FeedNotification> isReadSpec(boolean isRead) {
        return (root, query, builder) -> builder.equal(root.get("isRead"), isRead);
    }
}
