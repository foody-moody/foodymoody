package com.foodymoody.be.acceptance.comment_heart;

import static com.foodymoody.be.acceptance.comment_heart.CommentHeartSteps.댓글에_좋아요를_누른다;
import static com.foodymoody.be.acceptance.comment_heart.CommentHeartSteps.댓글에_좋아요를_취소한다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.피드에_댓글을_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.image.ImageSteps.피드_이미지를_업로드한다;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import io.restassured.builder.RequestSpecBuilder;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("댓글 좋아요 인수 테스트")
class FeedCommentFeedLikeAcceptanceTest extends AcceptanceTest {

    String feedId;
    String commentId;

    @BeforeEach
    void setUp() {
        var imageResponse1 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
        String id1 = imageResponse1.jsonPath().getString("id");
        var imageResponse2 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
        String id2 = imageResponse2.jsonPath().getString("id");
        List<String> imageIds = List.of(id1, id2);

        feedId = 피드를_등록하고_아이디를_받는다(회원푸반_액세스토큰, imageIds);
        commentId = 피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원아티_액세스토큰);
    }

    @DisplayName("댓글 좋아요를 누르면 좋아요가 반영된다.")
    @Test
    void createCommentHeart() {
        // docs
        api_문서_타이틀("comment_heart_register_success", spec);

        // when
        var response = 댓글에_좋아요를_누른다(회원푸반_액세스토큰, commentId, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(201);
    }

    @DisplayName("댓글 좋아요를 취소하면 좋아요가 취소된다.")
    @Test
    void deleteCommentHeart() {
        // docs
        api_문서_타이틀("comment_heart_cancel_success", spec);

        // given
        댓글에_좋아요를_누른다(회원푸반_액세스토큰, commentId, new RequestSpecBuilder().build());

        // when
        var response = 댓글에_좋아요를_취소한다(회원푸반_액세스토큰, commentId, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(204);
    }
}
