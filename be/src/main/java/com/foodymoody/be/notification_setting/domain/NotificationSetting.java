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
    private boolean isFeedLike;
    private boolean isCollectionLike;
    private boolean isCommentLike;
    private boolean isFollow;
    private boolean isFeedComment;
    private boolean isCollectionComment;

    public NotificationSetting(
            NotificationSettingId id, MemberId memberId, boolean isFeedLike, boolean isCollectionLike,
            boolean isCommentLike,
            boolean isFollow, boolean isFeedComment, boolean isCollectionComment
    ) {
        this.id = id;
        this.memberId = memberId;
        this.isFeedLike = isFeedLike;
        this.isCollectionLike = isCollectionLike;
        this.isCommentLike = isCommentLike;
        this.isFollow = isFollow;
        this.isFeedComment = isFeedComment;
        this.isCollectionComment = isCollectionComment;
    }


    public void update(
            boolean isFeedLike, boolean isCollectionLike, boolean isReplyLike, boolean isFollow,
            boolean isFeedComment, boolean isCollectionComment
    ) {
        this.isFeedLike = isFeedLike;
        this.isCollectionLike = isCollectionLike;
        this.isCommentLike = isReplyLike;
        this.isFollow = isFollow;
        this.isFeedComment = isFeedComment;
        this.isCollectionComment = isCollectionComment;
    }

    public void updateAll(boolean allow) {
        this.isFeedLike = allow;
        this.isCollectionLike = allow;
        this.isCommentLike = allow;
        this.isFollow = allow;
        this.isFeedComment = allow;
        this.isCollectionComment = allow;
    }
}
