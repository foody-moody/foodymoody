package com.foodymoody.be.notification.application;

import static com.foodymoody.be.common.event.NotificationType.COMMENT_ADDED_EVENT;
import static com.foodymoody.be.common.event.NotificationType.COMMENT_HEART_ADDED_EVENT;
import static com.foodymoody.be.common.event.NotificationType.REPLY_ADDED_EVENT;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification.domain.NotificationSummary;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class NotificationSummarySpecs {

    private NotificationSummarySpecs() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<NotificationSummary> searchByType(boolean isComment, boolean isHeart, boolean isFeed) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (isComment) {
                predicates.add(builder.equal(root.get("type"), REPLY_ADDED_EVENT));
            }
            if (isHeart) {
                predicates.add(builder.equal(root.get("type"), COMMENT_HEART_ADDED_EVENT));
            }
            if (isFeed) {
                predicates.add(builder.equal(root.get("type"), COMMENT_ADDED_EVENT));
            }
            return builder.or(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<NotificationSummary> isToMemberSpec(MemberId memberId) {
        return (root, query, builder) -> builder.equal(root.get("toMemberId"), memberId);
    }

    public static Specification<NotificationSummary> distinctById() {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        };
    }
}
