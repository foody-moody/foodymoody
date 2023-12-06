package com.foodymoody.be.notification_setting.domain;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationSettingId;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class NotificationSetting {

    @EmbeddedId
    private NotificationSettingId id;
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    private boolean isComment;
    private boolean isHeart;
    private boolean isFeed;

    public NotificationSetting(NotificationSettingId id, MemberId memberId, boolean isComment, boolean isHeart,
            boolean isFeed) {
        this.id = id;
        this.memberId = memberId;
        this.isComment = isComment;
        this.isHeart = isHeart;
        this.isFeed = isFeed;
    }

    public static NotificationSetting of(NotificationSettingId notificationSettingId, MemberId memberid) {
        return new NotificationSetting(notificationSettingId, memberid, true, true, true);
    }

    public NotificationSettingId getId() {
        return id;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public boolean isComment() {
        return isComment;
    }

    public boolean isHeart() {
        return isHeart;
    }

    public boolean isFeed() {
        return isFeed;
    }

    public void update(boolean heart, boolean comment, boolean feed) {
        this.isHeart = heart;
        this.isComment = comment;
        this.isFeed = feed;
    }
}
