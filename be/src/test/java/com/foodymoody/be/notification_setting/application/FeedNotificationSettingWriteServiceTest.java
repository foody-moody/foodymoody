package com.foodymoody.be.notification_setting.application;

import static com.foodymoody.be.notification_setting.util.TestFixture.memberAlbert;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.member.repository.MemberRepository;
import com.foodymoody.be.notification_setting.domain.NotificationSettingRepository;
import com.foodymoody.be.utils.SpringBootIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("알림 설정 서비스")
@SpringBootIntegrationTest
class FeedNotificationSettingWriteServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private NotificationSettingWriteService notificationSettingWriteService;
    @Autowired
    private NotificationSettingRepository notificationSettingRepository;

    @DisplayName("회원이 저장되면 알림 설정이 저장된다.")
    @Test
    void saveNotificationSetting() {
        // when
        memberRepository.save(memberAlbert());

        // then
        assertThat(notificationSettingRepository.findByMemberId("1")).isPresent();
    }
}
