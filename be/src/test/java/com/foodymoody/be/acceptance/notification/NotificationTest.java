package com.foodymoody.be.acceptance.notification;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import com.foodymoody.be.acceptance.member.MemberSteps;
import com.foodymoody.be.comment.domain.CommentAddNotificationEvent;
import com.foodymoody.be.common.event.NotificationEvents;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.member.repository.MemberRepository;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.time.LocalDateTime;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class NotificationTest extends AcceptanceTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();
    }

    @DisplayName("알람 요청 성공하면 응답코드 200과 알람을 받는다.")
    @Test
    void when_request_notification_if_success_return_200_and_receive_notification() {
        // given
        String 보노_아이디 = 회원보노가_회원가입하고_아이디를_반환한다();
        LocalDateTime createdAt = LocalDateTime.of(2021, 1, 1, 1, 1, 1);
        CommentAddNotificationEvent event = CommentAddNotificationEvent.of(보노_아이디, "피드에 새로운 댓글이 추가했습니다",
                NotificationType.COMMENT_ADDED,
                createdAt);
        NotificationEvents.publish(event);

        // when
        var response = 회원의_모든_알람을_조회한다(보노_아이디);

        // then
        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200)
        );
    }

    @DisplayName("알람을 안읽음으로 요청하면 응답코드 204과 알람을 안읽음으로 변경한다.")
    @Test
    void when_change_notification_status_if_success_then_return_204() {
        // given
        String 보노_아이디 = 회원보노가_회원가입하고_아이디를_반환한다();
        LocalDateTime createdAt = LocalDateTime.of(2021, 1, 1, 1, 1, 1);
        CommentAddNotificationEvent event = CommentAddNotificationEvent.of(보노_아이디, "피드에 새로운 댓글이 추가했습니다",
                NotificationType.COMMENT_ADDED,
                createdAt);
        NotificationEvents.publish(event);
        var 알람목록 = 회원의_모든_알람을_조회한다(보노_아이디);
        String eventId = 알람목록.jsonPath().getList("content.id", String.class).get(0);
        Map<String, Object> body = Map.of("isRead", true);

        // when
        var response = RestAssured.given().log().all()
                .body(body).contentType("application/json;charset=UTF-8").accept("application/json;charset=UTF-8")
                .when().put("/api/notifications/{memberId}/{eventId}", 보노_아이디, eventId)
                .then().log().all()
                .extract();

        // then
        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(204)
        );
    }

    private static ExtractableResponse<Response> 회원의_모든_알람을_조회한다(String 회원_아이디) {
        return RestAssured.given().log().all()
                .when().get("/api/notifications/{memberId}", 회원_아이디)
                .then().log().all()
                .extract();
    }

    private static String 회원보노가_회원가입하고_아이디를_반환한다() {
        return MemberSteps.회원보노가_회원가입한다(new RequestSpecBuilder().build()).jsonPath().getString("id");
    }
}
