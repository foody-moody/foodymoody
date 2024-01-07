package com.foodymoody.be.acceptance.notification;

import static com.foodymoody.be.acceptance.comment.CommentSteps.피드에_댓글을_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.모든_알림을_읽음으로_변경한다;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.알림_아이디로_알림을_조회한다;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.알림을_삭제한다;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.알림을_읽음으로_변경;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.유저의_모든_읽음_알림을_삭제한다;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.응답코드가_200;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.응답코드가_204;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.회원의_모든_알림을_조회하고_첫번째_알림을_가져온다;
import static com.foodymoody.be.acceptance.notification.NotificationSteps.회원의_모든_알림을_조회한다;

import com.foodymoody.be.acceptance.AcceptanceTest;
import com.foodymoody.be.auth.infra.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("알림 관련 기능")
class FeedNotificationAcceptanceTest extends AcceptanceTest {

    @Autowired
    JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        var feedId = 피드를_등록하고_아이디를_받는다(회원아티_액세스토큰);
        피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원푸반_액세스토큰);
        피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원푸반_액세스토큰);
        피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원푸반_액세스토큰);
        피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원푸반_액세스토큰);
        피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원푸반_액세스토큰);
    }

    @DisplayName("전체 알림 요청 성공하면 응답코드 200과 알림을 받는다.")
    @Test
    void when_request_all_notifications_if_success_return_200_and_receive_notifications() {
        // docs
        api_문서_타이틀("notification_request_all_success", spec);

        // when
        var response = 회원의_모든_알림을_조회한다(회원아티_액세스토큰, spec);

        // then
        응답코드가_200(response);
    }

    @DisplayName("모든 읽음 알림을 모두 삭제하면 응답코드 204를 반환한다")
    @Test
    void when_delete_all_read_if_success_then_return_code_204() {
        // docs
        api_문서_타이틀("notification_delete_all_read", spec);

        // when
        var response = 유저의_모든_읽음_알림을_삭제한다(회원아티_액세스토큰, spec);

        // then
        응답코드가_204(response);
    }

    @DisplayName("알림이 존재하고")
    @Nested
    class ExistsNotifications {

        String 알림_아이디;

        @BeforeEach
        void setUp() {
            알림_아이디 = 회원의_모든_알림을_조회하고_첫번째_알림을_가져온다(회원아티_액세스토큰);
        }


        @DisplayName("개별 알림 요청 성공하면 응답코드 200과 알림을 받는다.")
        @Test
        void when_request_single_notification_if_success_return_200_and_receive_notification() {
            // docs
            api_문서_타이틀("notification_request_single_success", spec);

            // when
            var response = 알림_아이디로_알림을_조회한다(알림_아이디, 회원아티_액세스토큰, spec);

            // then
            응답코드가_200(response);
        }

        @DisplayName("모든 알림을 읽음으로 요청 성공하면 응답코드 204를 반환한다.")
        @Test
        void when_request_mark_all_read_if_success_return_code_204() {
            // docs
            api_문서_타이틀("notification_mark_all_read_success", spec);

            // when
            var response = 모든_알림을_읽음으로_변경한다(회원아티_액세스토큰, spec);

            // then
            응답코드가_204(response);
        }

        @DisplayName("알림을 읽음으로 요청하면 응답코드 204과 알림을 읽음으로 변경한다.")
        @Test
        void when_request_delete_all_read_is_success_return_code_204() {
            // docs
            api_문서_타이틀("notification_delete_all_read_success", spec);

            // when
            var response = 알림을_읽음으로_변경(알림_아이디, 회원아티_액세스토큰, spec);

            // then
            응답코드가_204(response);
        }

        @DisplayName("알림을 삭제하면 응답코드 204가 반환된다.")
        @Test
        void when_delete_notification_if_success_then_return_code_204() {
            // docs
            api_문서_타이틀("notification_delete_success", spec);

            // when
            var response = 알림을_삭제한다(알림_아이디, 회원아티_액세스토큰, spec);

            // then
            응답코드가_204(response);
        }
    }
}
