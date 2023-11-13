package com.foodymoody.be.acceptance.notification;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.member.MemberSteps;
import com.foodymoody.be.comment.domain.CommentAddNotificationEvent;
import com.foodymoody.be.common.event.NotificationEvents;
import com.foodymoody.be.common.event.NotificationType;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.time.LocalDateTime;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;

public class NotificationSteps {

    public static ExtractableResponse<Response> 회원의_모든_알람을_조회한다(String 회원_아이디) {
        return 회원의_모든_알람을_조회한다(회원_아이디, new RequestSpecBuilder().build());
    }

    public static ExtractableResponse<Response> 회원의_모든_알람을_조회한다(String 회원_아이디, RequestSpecification spec) {
        return RestAssured.given().log().all().spec(spec).when().get("/api/notifications/{memberId}", 회원_아이디).then()
                .log().all().extract();
    }

    public static String 회원보노가_회원가입하고_아이디를_반환한다() {
        return MemberSteps.회원보노가_회원가입한다(new RequestSpecBuilder().build()).jsonPath().getString("id");
    }

    public static ExtractableResponse<Response> 알람을_읽음으로_변경(String 회원_아이디, String 알람_아이디, RequestSpecification spec) {
        Map<String, Object> body = Map.of("isRead", true);
        return RestAssured.given().log().all().spec(spec).body(body).contentType("application/json;charset=UTF-8")
                .accept("application/json;charset=UTF-8").when()
                .put("/api/notifications/{memberId}/{eventId}", 회원_아이디, 알람_아이디).then().log().all().extract();
    }

    public static ExtractableResponse<Response> 유저의_모든_알람을_삭제한다(String 회원_아이디, RequestSpecification spec) {
        return RestAssured.given().log().all().spec(spec).when().delete("/api/notifications/{memberId}", 회원_아이디).then()
                .log().all().extract();
    }

    public static ExtractableResponse<Response> 알람을_삭제한다(String 알람_아이디, String 회원_아이디, RequestSpecification spec) {
        return RestAssured.given().log().all().spec(spec).when()
                .delete("/api/notifications/{memberId}/{eventId}", 회원_아이디, 알람_아이디).then().log().all().extract();
    }

    public static String 회원의_모든_알람을_조회하고_첫번째_알람을_가져온다(String 회원_아이디) {
        return 회원의_모든_알람을_조회한다(회원_아이디).jsonPath().getList("content.id", String.class).get(0);
    }

    @NotNull
    public static CommentAddNotificationEvent createCommentAddNotification(String 회원_아이디, LocalDateTime createdAt) {
        return CommentAddNotificationEvent.of(회원_아이디, "피드에 새로운 댓글이 추가했습니다", NotificationType.COMMENT_ADDED, createdAt);
    }

    public static void 응답코드가_204(ExtractableResponse<Response> response) {
        Assertions.assertAll(() -> assertThat(response.statusCode()).isEqualTo(204));
    }

    public static void 응답코드가_200(ExtractableResponse<Response> response) {
        Assertions.assertAll(() -> assertThat(response.statusCode()).isEqualTo(200));
    }

    public static void 알람_발행(String 회원_아이디) {
        LocalDateTime createdAt = LocalDateTime.of(2021, 1, 1, 1, 1, 1);
        CommentAddNotificationEvent event = createCommentAddNotification(회원_아이디, createdAt);
        NotificationEvents.publish(event);
    }

    public static ExtractableResponse 알람_아이디로_알람을_조회한다(String 알람_아이디, String 회원_아이디, RequestSpecification spec) {
        return RestAssured.given().log().all().spec(spec)
                .when().get("/api/members/{memberId}/notifications/{notificationId}", 회원_아이디, 알람_아이디)
                .then().log().all()
                .extract();
    }
}
