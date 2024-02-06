package com.foodymoody.be.acceptance.feed_reply_like;

import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.피드에_댓글을_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed_reply.FeedReplySteps.댓글에_댓글을_등록한다;
import static com.foodymoody.be.acceptance.feed_reply.FeedReplySteps.댓글의_댓글을_조회한다;
import static com.foodymoody.be.acceptance.feed_reply_like.FeedReplyLikeSteps.대댓글에_좋아요를_추가한다;
import static com.foodymoody.be.acceptance.feed_reply_like.FeedReplyLikeSteps.대댓글의_좋아요를_취소한다;
import static com.foodymoody.be.acceptance.image.ImageSteps.피드_이미지를_업로드한다;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import java.util.List;
import org.jetbrains.annotations.NotNull;
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
        List<String> imageIds = getImageIds();

        feedId = 피드를_등록하고_아이디를_받는다(회원푸반_액세스토큰, imageIds);
        commentId = 피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원아티_액세스토큰);
        댓글에_댓글을_등록한다(feedId, commentId, 회원푸반_액세스토큰);
        firstReplyId = 댓글의_댓글을_조회한다(commentId, feedId).jsonPath().getList("content.id", String.class).get(0);
    }

    @DisplayName("댓글의 댓글 좋아요를 누르면 좋아요가 반영된다.")
    @Test
    void createReplyHeart() {
        // docs
        api_문서_타이틀("reply_heart_register_success", spec);

        // when
        var response = 대댓글에_좋아요를_추가한다(feedId, commentId, firstReplyId, 회원푸반_액세스토큰, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(201);
    }

    @DisplayName("댓글의 댓글 좋아요를 취소하면 좋아요가 취소된다.")
    @Test
    void deleteReplyHeart() {
        // docs
        api_문서_타이틀("reply_heart_cancel_success", spec);

        // given
        대댓글에_좋아요를_추가한다(feedId, commentId, firstReplyId, 회원푸반_액세스토큰);

        // when
        var response = 대댓글의_좋아요를_취소한다(feedId, commentId, firstReplyId, 회원푸반_액세스토큰, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(204);
    }

    @NotNull
    private List<String> getImageIds() {
        String id1 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec).jsonPath().getString("id");
        String id2 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec).jsonPath().getString("id");
        List<String> imageIds = List.of(id1, id2);
        return imageIds;
    }
}
