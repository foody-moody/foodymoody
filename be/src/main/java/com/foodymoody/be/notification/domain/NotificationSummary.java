package com.foodymoody.be.notification.domain;

import com.foodymoody.be.common.event.NotificationType;
import groovy.transform.Immutable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Immutable
@Subselect("SELECT _notification.id as id, _notification.from_member_id as member_id, _member.nickname as nickname,"
        + " _image.url as image_url, _notification.link as link,_notification.message as message, "
        + "_notification.created_at as created_at, _notification.updated_at as updated_at, _notification.type as type, "
        + "_notification.is_read as is_read, _notification.is_deleted as is_deleted, _notification.to_member_id as to_member_id "
        + "FROM notification _notification "
        + "LEFT JOIN member _member ON _notification.from_member_id = _member.id "
        + "LEFT JOIN image _image ON _member.profile_image_id = _image.id "
        + "WHERE _notification.is_deleted = false "
        + "AND _notification.is_read = false "
        + "ORDER BY _notification.created_at DESC")
@Synchronize({"notification", "member", "image"})
public class NotificationSummary {

    @Id
    private String id;
    private String memberId;
    private String toMemberId;
    private String nickname;
    private String imageUrl;
    private String link;
    private String message;
    @Enumerated(value = EnumType.STRING)
    private NotificationType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
