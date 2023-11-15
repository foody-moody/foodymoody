package com.foodymoody.be.acceptance.notification;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.comment.domain.CommentAddNotificationEvent;
import com.foodymoody.be.common.event.NotificationEvents;
import com.foodymoody.be.common.event.NotificationType;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;

public class NotificationSteps {

    public static ExtractableResponse<Response> 회원의_모든_알람을_조회한다(String accessToken) {
        return 회원의_모든_알람을_조회한다(accessToken, new RequestSpecBuilder().build());
    }

    public static ExtractableResponse<Response> 회원의_모든_알람을_조회한다(String accessToken, RequestSpecification spec) {
        return RestAssured
                .given().log().all().spec(spec).auth().oauth2(accessToken)
                .when().get("/api/notifications/")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 알람을_읽음으로_변경(String 알람_아이디, String accessToken,
            RequestSpecification spec) {
        Map<String, Object> body = Map.of("isRead", true);
        return RestAssured.given().log().all().spec(spec).auth().oauth2(accessToken).body(body)
                .contentType("application/json;charset=UTF-8")
                .accept("application/json;charset=UTF-8")
                .when().put("/api/notifications/{notificationId}", 알람_아이디)
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 알람을_일괄적으로_변경(List<String> 알람_아이디들, String accessToken,
            RequestSpecification spec) {
        Map<String, Object> body = new HashMap<>();
        body.put("isRead", true);
        body.put("notificationIds", 알람_아이디들);
        return RestAssured.given().log().all().spec(spec).auth().oauth2(accessToken).body(body)
                .contentType("application/json;charset=UTF-8")
                .accept("application/json;charset=UTF-8")
                .when().put("/api/notifications")
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 유저의_모든_알람을_삭제한다(String accessToken, RequestSpecification spec) {
        return RestAssured.given().log().all().spec(spec).auth().oauth2(accessToken).when().delete("/api/notifications")
                .then()
                .log().all().extract();
    }

    public static ExtractableResponse<Response> 알람을_삭제한다(String 알람_아이디, String accessToken, RequestSpecification spec) {
        return RestAssured.given().log().all().spec(spec).auth().oauth2(accessToken).when()
                .delete("/api/notifications/{eventId}", 알람_아이디).then().log().all().extract();
    }

    public static String 회원의_모든_알람을_조회하고_첫번째_알람을_가져온다(String accessToken) {
        return 회원의_모든_알람을_조회한다(accessToken).jsonPath().getList("content.id", String.class).get(0);
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

    public static ExtractableResponse 알람_아이디로_알람을_조회한다(String 알람_아이디, String accessToken, RequestSpecification spec) {
        return RestAssured.given().log().all().spec(spec).auth().oauth2(accessToken)
                .when().get("/api/notifications/{notificationId}", 알람_아이디)
                .then().log().all()
                .extract();
    }
}
