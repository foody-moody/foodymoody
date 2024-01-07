package com.foodymoody.be.acceptance.feed_collection_reply;

import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.피드_컬렉션_등록하고_아이디를_가져온다;
import static com.foodymoody.be.acceptance.feed_collection_comment.FeedCollectionCommentSteps.피드_컬렉션에_댓글을_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed_collection_reply.FeedCollectionReplySteps.피드_컬렉션_댓글에_대댓글을_등록하고_아이디를_반환한다;
import static com.foodymoody.be.acceptance.feed_collection_reply.FeedCollectionReplySteps.피드_컬렉션_댓글에_대댓글을_등록한다;
import static com.foodymoody.be.acceptance.feed_collection_reply.FeedCollectionReplySteps.피드_컬렉션_댓글의_대댓글을_삭제한다;
import static com.foodymoody.be.acceptance.feed_collection_reply.FeedCollectionReplySteps.피드_컬렉션_댓글의_대댓글을_수정한다;
import static com.foodymoody.be.acceptance.feed_collection_reply.FeedCollectionReplySteps.피드_컬렉션_댓글의_대댓글을_조회한다;
import static com.foodymoody.be.acceptance.feed_collection_reply_like.FeedCollectionReplyLikeSteps.피드_컬렉션_대댓글에_좋아요를_등록한다;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("피드 컬렉션 댓글에 대댓글 관련 기능")
class FeedCollectionReplyAcceptanceTest extends AcceptanceTest {

    String commentId;

    @Autowired
    EntityManager em;

    @DisplayName("피드 컬렉션 생성하고 댓글을 작성 한다")
    @BeforeEach
    void setUp() {
        List<String> feedIds = new ArrayList<>();
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰));
        String feedCollectionId = 피드_컬렉션_등록하고_아이디를_가져온다(feedIds, 회원아티_액세스토큰);
        commentId = 피드_컬렉션에_댓글을_등록하고_아이디를_받는다(회원아티_액세스토큰, feedCollectionId);
    }

    @DisplayName("댓글에 대댓글을 작성 요청 성공하면 응답 코드 201를 반환한다.")
    @Test
    void when_post_feed_collection_reply_if_success_then_respond_code_201() {
        // docs
        api_문서_타이틀("feed_collection_reply_post_success", spec);

        // when
        var response = 피드_컬렉션_댓글에_대댓글을_등록한다(회원아티_액세스토큰, commentId, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(201);
    }

    @DisplayName("대댓글 삭제 요청 성공하면 응답 코드 204를 반환한다.")
    @Test
    void when_delete_feed_collection_reply_if_success_then_respond_code_204() {
        // docs
        api_문서_타이틀("feed_collection_reply_delete_success", spec);

        // given
        var replyId = 피드_컬렉션_댓글에_대댓글을_등록하고_아이디를_반환한다(회원아티_액세스토큰, commentId);

        // when
        var response = 피드_컬렉션_댓글의_대댓글을_삭제한다(회원아티_액세스토큰, replyId, commentId, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(204);
    }

    @DisplayName("대댓글 수정 요청 성공하면 응답 코드 204를 반환한다.")
    @Test
    void when_update_feed_collection_reply_if_success_then_respond_code_204() {
        // docs
        api_문서_타이틀("feed_collection_reply_edit_success", spec);

        // given
        var replyId = 피드_컬렉션_댓글에_대댓글을_등록하고_아이디를_반환한다(회원아티_액세스토큰, commentId);

        // when
        var response = 피드_컬렉션_댓글의_대댓글을_수정한다(회원아티_액세스토큰, replyId, commentId, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(204);
    }

    @DisplayName("대댓글 전체 조회 요청 성공하면 응답 코드 200을 반환한다.")
    @Test
    void when_get_feed_collection_replies_if_success_then_respond_code_200() {
        // docs
        api_문서_타이틀("feed_collection_reply_fetch_success", spec);

        // given
        피드_컬렉션_댓글에_대댓글을_등록한다(회원아티_액세스토큰, commentId);
        피드_컬렉션_댓글에_대댓글을_등록한다(회원아티_액세스토큰, commentId);
        String id = 피드_컬렉션_댓글에_대댓글을_등록하고_아이디를_반환한다(회원아티_액세스토큰, commentId);
        피드_컬렉션_대댓글에_좋아요를_등록한다(회원푸반_액세스토큰, commentId, id);

        // when
        var response = 피드_컬렉션_댓글의_대댓글을_조회한다(회원푸반_액세스토큰, commentId, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
    }
}
