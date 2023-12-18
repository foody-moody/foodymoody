package com.foodymoody.be.acceptance.feed_collection_comment;

import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.피드_커렉션_등록한다;
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

@DisplayName("피드 컬렉션 댓글")
class FeedCollectionCommentAcceptanceTest extends AcceptanceTest {

    String feedCollectionId;

    @DisplayName("피드를 등록하 컬렉션을 생성한다.")
    @BeforeEach
    void setUp() {
        List<String> feedIds = new ArrayList<>();
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰));
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰));
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰));
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰));
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰));
        feedCollectionId = 피드_커렉션_등록한다(feedIds, 회원아티_액세스토큰, spec).jsonPath().getString("id");
    }

    @DisplayName("피드 컬렉션 댓글을 작성 요청 성공하면 응답 코드 204를 반환한다.")
    @Test
    void when_request_to_post_feed_collection_comment_then_respond_code_204() {
        // docs
        api_문서_타이틀("feed_collection_comment_post_success", spec);

        // when
        Map<String, Object> body = new HashMap<>();
        body.put("content", "댓글 내용");
        var response = RestAssured
                .given()
                .spec(spec)
                .log().all()
                .auth().oauth2(회원아티_액세스토큰)
                .body(body).contentType("application/json")
                .when()
                .post("/api/feed_collections/{id}/comments", feedCollectionId)
                .then()
                .log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(204);
    }
}
