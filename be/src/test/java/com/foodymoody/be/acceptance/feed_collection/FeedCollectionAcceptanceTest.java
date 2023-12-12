package com.foodymoody.be.acceptance.feed_collection;

import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록하고_아이디를_받는다;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import io.restassured.RestAssured;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedCollectionAcceptanceTest extends AcceptanceTest {

    List<String> feedIds;


    @BeforeEach
    void setUp() {
        feedIds = new ArrayList<>();
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰));
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰));
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰));
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰));
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰));
    }

    @DisplayName("피드 컬렉션을 생성 요청이 성공하면 201을 반환한다.")
    @Test
    void when_request_create_collection_if_success_then_return_code_201() {
        // docs
        api_문서_타이틀("feed_collection_fetch_all", spec);

        // when
        Map<String, Object> body = new HashMap<>();
        body.put("title", "테스트 컬렉션");
        body.put("description", "테스트 컬렉션입니다.");
        body.put("thumbnailUrl", "https://thumbnail.url");
        body.put("private", false);
        body.put("feedIds", feedIds);
        var response = RestAssured.given()
                .spec(spec)
                .log().all()
                .auth().oauth2(회원아티_액세스토큰)
                .body(body).contentType("application/json")
                .when()
                .post("/api/collections")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(201);
    }

    @DisplayName("피드 컬렉션을 전체 조회 성공하면 200을 반환한다.")
    @Test
    void when_request_fetch_all_collection_if_success_then_return_code_200() {
        // given
        // when
        // then
    }

}
