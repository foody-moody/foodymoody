package com.foodymoody.be.notification_setting.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.ids.IdFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotificationSettingTest {

    @DisplayName("알림 설정을 수정한다")
    @Test
    void update() {
        // given
        var notificationSettingId = IdFactory.createNotificationSettingId();
        var memberId = IdFactory.createMemberId();
        var notificationSetting = new NotificationSetting(
                notificationSettingId, memberId, true, true, true, true, true, true
        );

        // when
        notificationSetting.update(false, false, false, false, false, false);

        // then
        assertThat(notificationSetting).hasFieldOrPropertyWithValue("isFeedLike", false)
                .hasFieldOrPropertyWithValue("isCollectionLike", false)
                .hasFieldOrPropertyWithValue("isCommentLike", false)
                .hasFieldOrPropertyWithValue("isFollow", false)
                .hasFieldOrPropertyWithValue("isFeedComment", false)
                .hasFieldOrPropertyWithValue("isCollectionComment", false);
    }

    @DisplayName("전체 알림 설정을 수정한다")
    @Test
    void updateAll() {
        // given
        var notificationSettingId = IdFactory.createNotificationSettingId();
        var memberId = IdFactory.createMemberId();
        var notificationSetting = new NotificationSetting(
                notificationSettingId, memberId, true, true, true, true, true, true
        );

        // when
        notificationSetting.updateAll(false);

        // then
        assertThat(notificationSetting).hasFieldOrPropertyWithValue("isFeedLike", false)
                .hasFieldOrPropertyWithValue("isCollectionLike", false)
                .hasFieldOrPropertyWithValue("isCommentLike", false)
                .hasFieldOrPropertyWithValue("isFollow", false)
                .hasFieldOrPropertyWithValue("isFeedComment", false)
                .hasFieldOrPropertyWithValue("isCollectionComment", false);
    }

}
