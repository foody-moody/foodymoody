package com.foodymoody.be.acceptance.notification;

import static com.foodymoody.be.acceptance.notification.NotificationSteps.알람_발행;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.알람_아이디로_알람을_조회한다;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.알람을_삭제한다;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.알람을_읽음으로_변경;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.유저의_모든_알람을_삭제한다;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.응답코드가_200;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.응답코드가_204;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.회원보노가_회원가입하고_아이디를_반환한다;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.회원의_모든_알람을_조회하고_첫번째_알람을_가져온다;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.회원의_모든_알람을_조회한다;

import com.foodymoody.be.acceptance.AcceptanceTest;
import com.foodymoody.be.acceptance.util.DatabaseCleanup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class NotificationAcceptanceTest extends AcceptanceTest {

    @Autowired
    DatabaseCleanup cleanup;
    String 보노_아이디;

    @BeforeEach
    void setUp() {
        cleanup.execute();
        보노_아이디 = 회원보노가_회원가입하고_아이디를_반환한다();
        알람_발행(보노_아이디);
    }

    @DisplayName("전체 알람 요청 성공하면 응답코드 200과 알람을 받는다.")
    @Test
    void when_request_all_notifications_if_success_return_200_and_receive_notifications() {
        // docs
        api_문서_타이틀("notification_request_all_success", spec);

        // when
        var response = 회원의_모든_알람을_조회한다(보노_아이디, spec);

        // then
        응답코드가_200(response);
    }

    @DisplayName("개별 알람 요청 성공하면 응답코드 200과 알람을 받는다.")
    @Test
    void when_request_single_notification_if_success_return_200_and_receive_notification() {
        // docs
        api_문서_타이틀("notification_request_single_success", spec);

        // given
        var 알람_아이디 = 회원의_모든_알람을_조회하고_첫번째_알람을_가져온다(보노_아이디);

        // when
        var response = 알람_아이디로_알람을_조회한다(알람_아이디, 보노_아이디, spec);

        // then
        응답코드가_200(response);
    }

    @DisplayName("알람을 읽음으로 요청하면 응답코드 204과 알람을 읽음으로 변경한다.")
    @Test
    void when_change_notification_status_if_success_then_return_204() {
        // docs
        api_문서_타이틀("notification_change_status_success", spec);

        // given
        var 알람_아이디 = 회원의_모든_알람을_조회하고_첫번째_알람을_가져온다(보노_아이디);

        // when
        var response = 알람을_읽음으로_변경(보노_아이디, 알람_아이디, spec);

        // then
        응답코드가_204(response);
    }

    @DisplayName("알람을 삭제하면 응답코드 204과 알람을 삭제한다.")
    @Test
    void when_delete_notification_if_success_then_return_code_204() {
        // docs
        api_문서_타이틀("notification_delete_success", spec);

        // given
        var 알람_아이디 = 회원의_모든_알람을_조회하고_첫번째_알람을_가져온다(보노_아이디);

        // when
        var response = 알람을_삭제한다(알람_아이디, 보노_아이디, spec);

        // then
        응답코드가_204(response);
    }

    @DisplayName("알람을 모두 삭제하면 응답코드 204와 알람을 모두 삭제한다.")
    @Test
    void when_delete_all_notification_if_success_then_return_code_204() {
        // docs
        api_문서_타이틀("notification_delete_all_success", spec);

        // when
        var response = 유저의_모든_알람을_삭제한다(보노_아이디, spec);

        // then
        응답코드가_204(response);
    }
}
