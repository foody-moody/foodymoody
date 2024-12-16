package com.foodymoody.be.notification_setting.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.ids.IdFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotificationMapperTest {

    @DisplayName("맴버 아이디와 알림 설정 아이디를 받아서 알림 설정 객체를 반환한다.")
    @Test
    void toNotificationSetting() {
        // given
        var memberId = IdFactory.createMemberId();
        var notificationSettingId = IdFactory.createNotificationSettingId();

        // when
        var notificationSetting = NotificationMapper.toNotificationSetting(memberId, notificationSettingId);

        // then
        assertThat(notificationSetting)
                .hasFieldOrPropertyWithValue("id", notificationSettingId)
                .hasFieldOrPropertyWithValue("memberId", memberId)
                .hasFieldOrPropertyWithValue("isFeedLike", true)
                .hasFieldOrPropertyWithValue("isCollectionLike", true)
                .hasFieldOrPropertyWithValue("isCommentLike", true)
                .hasFieldOrPropertyWithValue("isFollow", true)
                .hasFieldOrPropertyWithValue("isFeedComment", true)
                .hasFieldOrPropertyWithValue("isCollectionComment", true);
    }

}
