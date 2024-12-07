package com.foodymoody.be.notification.domain;

import com.foodymoody.be.common.util.ids.MemberId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

/**
 * The NotificationSummaryDao interface provides methods for retrieving notification summaries.
 */
public interface NotificationSummaryDao {

    /**
     * Retrieves all notification summaries for a specific member.
     *
     * @param memberId The ID of the member
     * @param pageable The page information
     * @return A slice of NotificationSummary objects representing the retrieved notification summaries
     */
    Slice<NotificationSummary> findAllByMemberId(MemberId memberId, Pageable pageable);

}
