package com.foodymoody.be.acceptance.reply_heart;

import static com.foodymoody.be.acceptance.comment.CommentSteps.피드에_댓글을_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.image.ImageSteps.피드_이미지를_업로드한다;
import static com.foodymoody.be.acceptance.reply.ReplySteps.댓글에_댓글을_등록한다;
import static com.foodymoody.be.acceptance.reply.ReplySteps.댓글의_댓글을_조회한다;
import static com.foodymoody.be.acceptance.reply_heart.ReplyHeartSteps.대댓글에_좋아요를_추가한다;
import static com.foodymoody.be.acceptance.reply_heart.ReplyHeartSteps.대댓글의_좋아요를_취소한다;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("댓글의 댓글 좋아요 인수 테스트")
class FeedReplyFeedLikeAcceptanceTest extends AcceptanceTest {

    String feedId;
    String commentId;
    String firstReplyId;

    @BeforeEach
    void setUp() {
        var imageResponse1 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
        String id1 = imageResponse1.jsonPath().getString("id");
        var imageResponse2 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
        String id2 = imageResponse2.jsonPath().getString("id");
        List<String> imageIds = List.of(id1, id2);

        feedId = 피드를_등록하고_아이디를_받는다(회원푸반_액세스토큰, imageIds);
        commentId = 피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원아티_액세스토큰);
        댓글에_댓글을_등록한다(feedId, commentId, 회원푸반_액세스토큰);
        firstReplyId = 댓글의_댓글을_조회한다(commentId).jsonPath().getList("content.id", String.class).get(0);
    }

    @DisplayName("댓글의 댓글 좋아요를 누르면 좋아요가 반영된다.")
    @Test
    void createReplyHeart() {
        // docs
        api_문서_타이틀("reply_heart_register_success", spec);

        // when
        var response = 대댓글에_좋아요를_추가한다(회원푸반_액세스토큰, commentId, firstReplyId, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(201);
    }

    @DisplayName("댓글의 댓글 좋아요를 취소하면 좋아요가 취소된다.")
    @Test
    void deleteReplyHeart() {
        // docs
        api_문서_타이틀("reply_heart_cancel_success", spec);

        // given
        대댓글에_좋아요를_추가한다(회원푸반_액세스토큰, commentId, firstReplyId);

        // when
        var response = 대댓글의_좋아요를_취소한다(회원푸반_액세스토큰, commentId, firstReplyId, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(204);
    }
}