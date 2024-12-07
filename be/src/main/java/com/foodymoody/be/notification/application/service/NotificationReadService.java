package com.foodymoody.be.notification.application.service;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification.domain.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The NotificationReadService class provides methods to read notifications.
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class NotificationReadService {

    /**
     * Represents a repository for managing notifications.
     */
    private final NotificationRepository notificationRepository;

    /**
     * Retrieves the number of unread notifications for a specific member.
     *
     * @param memberId The ID of the member
     * @return The number of unread notifications
     */
    public long fetchCountNotReadNotification(MemberId memberId) {
        return notificationRepository.countByMemberIdAndDeletedAndRead(memberId, false, false);
    }

}
