package com.foodymoody.be.acceptance.comment_heart;

import static com.foodymoody.be.acceptance.comment.CommentSteps.피드에_댓글을_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록하고_아이디를_받는다;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentHeartAcceptanceTest extends AcceptanceTest {

    String feedId;
    String commentId;

    @BeforeEach
    void setUp() {
        feedId = 피드를_등록하고_아이디를_받는다(회원푸반_액세스토큰);
        commentId = 피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원아티_액세스토큰);
    }

    @DisplayName("댓글 좋아요를 누르면 좋아요가 반영된다.")
    @Test
    void createCommentHeart() {
        // when
        var response = RestAssured.given().log().all().auth().oauth2(회원푸반_액세스토큰).contentType("application/json").when()
                .post("/api/comments/{commentId}/hearts", commentId).then().log().all().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(201);
    }
}
