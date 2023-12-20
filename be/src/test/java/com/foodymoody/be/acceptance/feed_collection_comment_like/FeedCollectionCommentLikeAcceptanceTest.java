package com.foodymoody.be.acceptance.feed_collection_comment_like;


import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.피드_컬렉션_등록하고_아이디를_가져온다;
import static com.foodymoody.be.acceptance.feed_collection_comment.FeedCollectionCommentSteps.피드_컬렉션에_댓글을_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed_collection_comment_like.FeedCollectionCommentLikeSteps.피드_컬렉션_댓글에_좋아요를_등록한다;
import static com.foodymoody.be.acceptance.feed_collection_comment_like.FeedCollectionCommentLikeSteps.피드_컬렉션_댓글에_좋아요를_취소한다;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedCollectionCommentLikeAcceptanceTest extends AcceptanceTest {

    String commentId;

    @DisplayName("피드 컬렉션 생성하고 댓글을 작성 한다")
    @BeforeEach
    void setUp() {
        List<String> feedIds = new ArrayList<>();
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰));
        String feedCollectionId = 피드_컬렉션_등록하고_아이디를_가져온다(feedIds, 회원아티_액세스토큰);
        commentId = 피드_컬렉션에_댓글을_등록하고_아이디를_받는다(회원아티_액세스토큰, feedCollectionId);
    }

    @DisplayName("피드 컬렉션 댓글 좋아요 등록 요청 성공하면 응답 코드 201를 반환한다.")
    @Test
    void when_post_feed_collection_comment_like_if_success_then_respond_code_201() {
        // docs
        api_문서_타이틀("feed_collection_comment_like_post_success", spec);

        // when
        var response = 피드_컬렉션_댓글에_좋아요를_등록한다(회원아티_액세스토큰, commentId, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(201);
    }

    @DisplayName("피드 컬렉션 댓글 좋아요 취소 요청 성공하면 응답 코드 204를 반환한다.")
    @Test
    void when_cancel_feed_collection_comment_like_if_success_then_respond_code_204() {
        // docs
        api_문서_타이틀("feed_collection_comment_like_cancel_success", spec);

        // given
        피드_컬렉션_댓글에_좋아요를_등록한다(회원아티_액세스토큰, commentId, spec);

        // when
        var response = 피드_컬렉션_댓글에_좋아요를_취소한다(회원아티_액세스토큰, commentId, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(204);
    }
}
