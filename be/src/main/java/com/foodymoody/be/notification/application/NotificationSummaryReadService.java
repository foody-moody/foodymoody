package com.foodymoody.be.notification.application;


import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification.domain.NotificationSummary;
import com.foodymoody.be.notification.domain.NotificationSummaryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

/**
 * The NotificationSummaryReadService class provides methods to read notification summaries.
 */
@RequiredArgsConstructor
@Service
public class NotificationSummaryReadService {

    /**
     * Represents a repository for managing notification summaries.
     */
    private final NotificationSummaryDao dao;

    /**
     * Retrieves all notification summaries for a specific member.
     *
     * @param memberId The ID of the member
     * @param pageable The page information
     * @return The retrieved notification summaries
     */
    public Slice<NotificationSummary> requestAll(MemberId memberId, Pageable pageable) {
        return dao.findAllByMemberId(memberId, pageable);
    }
}
