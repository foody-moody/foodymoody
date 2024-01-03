package com.foodymoody.be.notification_setting.application;

import static com.foodymoody.be.notification_setting.util.TestFixture.memberAlbert;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.infra.persistence.MemberJpaRepository;
import com.foodymoody.be.notification_setting.infra.persistence.jpa.NotificationSettingJpaRepository;
import com.foodymoody.be.utils.SpringBootIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("알림 설정 서비스")
@SpringBootIntegrationTest
class FeedNotificationSettingWriteServiceTest {

    @Autowired
    private MemberJpaRepository memberRepository;

    @Autowired
    private NotificationSettingJpaRepository notificationSettingRepository;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();
        notificationSettingRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        memberRepository.deleteAll();
        notificationSettingRepository.deleteAll();
    }

    @DisplayName("회원이 저장되면 알림 설정이 저장된다.")
    @Test
    void saveNotificationSetting() {
        // when
        MemberId memberId = memberAlbert().getId();

        // then
        assertThat(notificationSettingRepository.findByMemberId(memberId)).isPresent();
    }
}
