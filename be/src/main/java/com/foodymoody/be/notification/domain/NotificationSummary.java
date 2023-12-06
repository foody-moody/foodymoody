package com.foodymoody.be.notification.domain;

import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.MemberId;
import groovy.transform.Immutable;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
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
@Subselect(
        "SELECT _notification.id as id, _notification.from_member_id as from_member_id, _member.nickname as nickname,"
        + "_member_image.url as member_image_url,_notification.comment_id as comment_id, _notification.feed_id as feed_id,"
        + "_feed_image.url as feed_image_url,_notification.message as message,_notification.type as type, "
        + "_notification.created_at as created_at, _notification.updated_at as updated_at,_notification.is_read as is_read,"
        + "_notification.is_deleted as is_deleted, _notification.to_member_id as to_member_id "
        + "FROM feed_notification _notification "
        + "LEFT JOIN member _member ON _notification.from_member_id = _member.id "
        + "LEFT JOIN image _member_image ON _member.profile_image_id = _member_image.id "
        + "LEFT JOIN feed _feed ON _notification.feed_id = _feed.id "
        + "LEFT JOIN feed_image_menus_list _feed_image_menus_list ON _feed.id = _feed_image_menus_list.feed_id "
        + "LEFT JOIN image_menu _image_menus ON _feed_image_menus_list.image_menus_list_id = _image_menus.id "
        + "LEFT JOIN image _feed_image ON _image_menus.image_id = _feed_image.id "
        + "WHERE _notification.is_deleted = false "
        + "AND _image_menus.display_order = '0'"
        + "ORDER BY _notification.created_at DESC")
@Synchronize({"notification", "member", "image"})
public class NotificationSummary {

    @Id
    private String id;
    @AttributeOverride(name = "value", column = @Column(name = "to_member_id"))
    private MemberId toMemberId;
    @AttributeOverride(name = "value", column = @Column(name = "from_member_id"))
    private MemberId fromMemberId;
    private String nickname;
    private String memberImageUrl;
    private String commentId;
    private String feedId;
    private String feedImageUrl;
    private String message;
    @Enumerated(value = EnumType.STRING)
    private NotificationType type;
    private boolean isRead;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
