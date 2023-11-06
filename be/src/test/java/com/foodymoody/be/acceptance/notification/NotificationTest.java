package com.foodymoody.be.acceptance.notification;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import com.foodymoody.be.acceptance.member.MemberSteps;
import com.foodymoody.be.comment.domain.CommentAddNotificationEvent;
import com.foodymoody.be.common.event.NotificationEvents;
import com.foodymoody.be.common.event.NotificationType;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotificationTest extends AcceptanceTest {

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
        var response = RestAssured.given().log().all()
                .when().get("/api/notifications/{memberId}", 보노_아이디)
                .then().log().all()
                .extract();

        // then
        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200)
        );

    }

    private static String 회원보노가_회원가입하고_아이디를_반환한다() {
        return MemberSteps.회원보노가_회원가입한다(new RequestSpecBuilder().build()).jsonPath().getString("id");
    }

}
