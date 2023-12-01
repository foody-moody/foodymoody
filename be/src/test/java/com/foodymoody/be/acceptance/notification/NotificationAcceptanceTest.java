package com.foodymoody.be.acceptance.notification;

import static com.foodymoody.be.acceptance.comment.CommentSteps.피드에_댓글을_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.알람_아이디로_알람을_조회한다;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.알람을_삭제한다;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.알람을_일괄적으로_변경;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.알람을_일괄적으로_삭졔한다;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.알람을_읽음으로_변경;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.유저의_모든_알람을_삭제한다;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.응답코드가_200;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.응답코드가_204;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.회원의_모든_알람을_조회하고_첫번째_알람을_가져온다;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.회원의_모든_알람을_조회한다;

import com.foodymoody.be.acceptance.AcceptanceTest;
import com.foodymoody.be.auth.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("알림 관련 기능")
class NotificationAcceptanceTest extends AcceptanceTest {

    String 아티_아이디;
    @Autowired
    JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        아티_아이디 = jwtUtil.parseAccessToken(회원아티_액세스토큰).get("id");
        var feedId = 피드를_등록하고_아이디를_받는다(회원아티_액세스토큰);
        피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원푸반_액세스토큰);
        피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원푸반_액세스토큰);
        피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원푸반_액세스토큰);
        피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원푸반_액세스토큰);
        피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원푸반_액세스토큰);
    }

    @DisplayName("전체 알람 요청 성공하면 응답코드 200과 알람을 받는다.")
    @Test
    void when_request_all_notifications_if_success_return_200_and_receive_notifications() {
        // docs
        api_문서_타이틀("notification_request_all_success", spec);

        // when
        var response = 회원의_모든_알람을_조회한다(회원아티_액세스토큰, spec);

        // then
        응답코드가_200(response);
    }

    @DisplayName("알람을 모두 삭제하면 응답코드 204와 알람을 모두 삭제한다.")
    @Test
    void when_delete_all_notification_if_success_then_return_code_204() {
        // docs
        api_문서_타이틀("notification_delete_all_success", spec);

        // when
        var response = 유저의_모든_알람을_삭제한다(회원아티_액세스토큰, spec);

        // then
        응답코드가_204(response);
    }

    @DisplayName("알람이 존재하고")
    @Nested
    class ExistsNotifications {

        String 알람_아이디;

        @BeforeEach
        void setUp() {
            알람_아이디 = 회원의_모든_알람을_조회하고_첫번째_알람을_가져온다(회원아티_액세스토큰);
        }


        @DisplayName("개별 알람 요청 성공하면 응답코드 200과 알람을 받는다.")
        @Test
        void when_request_single_notification_if_success_return_200_and_receive_notification() {
            // docs
            api_문서_타이틀("notification_request_single_success", spec);

            // when
            var response = 알람_아이디로_알람을_조회한다(알람_아이디, 회원아티_액세스토큰, spec);

            // then
            응답코드가_200(response);
        }

        @DisplayName("알람을 일괄적으로 읽음으로 요청하면 응답코드 204와 알람을 읽음으로 변경한다.")
        @Test
        void when_change_all_notification_status_if_success_then_return_204() {
            // docs
            api_문서_타이틀("notification_change_all_status_success", spec);
            var 알람들 = 회원의_모든_알람을_조회한다(회원아티_액세스토큰, spec).jsonPath().getList("content.id", String.class);

            // when
            var response = 알람을_일괄적으로_변경(알람들, 회원아티_액세스토큰, spec);

            // then
            응답코드가_204(response);
        }

        @DisplayName("알람을 읽음으로 요청하면 응답코드 204과 알람을 읽음으로 변경한다.")
        @Test
        void when_change_notification_status_if_success_then_return_204() {
            // docs
            api_문서_타이틀("notification_change_status_success", spec);

            // when
            var response = 알람을_읽음으로_변경(알람_아이디, 회원아티_액세스토큰, spec);

            // then
            응답코드가_204(response);
        }

        @DisplayName("알람을 일괄적으로 삭제하면 응답코드 204가 반환된다.")
        @Test
        void when_delete_notifications_if_success_then_return_code_204() {
            // docs
            api_문서_타이틀("notification_delete_notification_list_success", spec);
            var 알람들 = 회원의_모든_알람을_조회한다(회원아티_액세스토큰, spec).jsonPath().getList("content.id", String.class);

            // when
            var response = 알람을_일괄적으로_삭졔한다(알람들, 회원아티_액세스토큰, spec);

            // then
            응답코드가_204(response);
        }

        @DisplayName("알람을 삭제하면 응답코드 204가 반환된다.")
        @Test
        void when_delete_notification_if_success_then_return_code_204() {
            // docs
            api_문서_타이틀("notification_delete_success", spec);

            // when
            var response = 알람을_삭제한다(알람_아이디, 회원아티_액세스토큰, spec);

            // then
            응답코드가_204(response);
        }

    }
}
