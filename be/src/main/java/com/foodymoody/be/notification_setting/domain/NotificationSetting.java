package com.foodymoody.be.notification_setting.domain;

import com.foodymoody.be.common.util.ids.NotificationSettingId;
import com.foodymoody.be.member.domain.MemberId;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class NotificationSetting {

    @EmbeddedId
    private NotificationSettingId id;
    private String memberId;
    private boolean isComment;
    private boolean isHeart;
    private boolean isFeed;

    public NotificationSetting(NotificationSettingId id, String memberId, boolean isComment, boolean isHeart,
            boolean isFeed) {
        this.id = id;
        this.memberId = memberId;
        this.isComment = isComment;
        this.isHeart = isHeart;
        this.isFeed = isFeed;
    }

    public static NotificationSetting of(NotificationSettingId notificationSettingId, MemberId memberid) {
        return new NotificationSetting(notificationSettingId, memberid.getId(), true, true, true);
    }

    public NotificationSettingId getId() {
        return id;
    }

    public String getMemberId() {
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
