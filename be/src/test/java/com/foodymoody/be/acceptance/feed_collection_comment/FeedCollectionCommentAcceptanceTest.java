package com.foodymoody.be.acceptance.feed_collection_comment;

import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.피드_컬렉션_등록하고_아이디를_가져온다;
import static com.foodymoody.be.acceptance.feed_collection_comment.FeedCollectionCommentSteps.피드_컬렉션에_댓글을_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed_collection_comment.FeedCollectionCommentSteps.피드_컬렉션에_댓글을_등록한다;
import static com.foodymoody.be.acceptance.feed_collection_comment.FeedCollectionCommentSteps.피드_컬렉션에_댓글을_삭제한다;
import static com.foodymoody.be.acceptance.feed_collection_comment.FeedCollectionCommentSteps.피드_컬렉션에_댓글을_수정한다;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("피드 컬렉션 댓글")
class FeedCollectionCommentAcceptanceTest extends AcceptanceTest {

    String feedCollectionId;

    @DisplayName("피드를 등록하고 컬렉션을 생성한다.")
    @BeforeEach
    void setUp() {
        List<String> feedIds = new ArrayList<>();
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰));
        feedCollectionId = 피드_컬렉션_등록하고_아이디를_가져온다(feedIds, 회원아티_액세스토큰);
    }

    @DisplayName("피드 컬렉션 댓글을 작성 요청 성공하면 응답 코드 204를 반환한다.")
    @Test
    void when_request_to_post_feed_collection_comment_then_respond_code_204() {
        // docs
        api_문서_타이틀("feed_collection_comment_post_success", spec);

        // when
        var response = 피드_컬렉션에_댓글을_등록한다(회원아티_액세스토큰, feedCollectionId, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(201);
    }

    @DisplayName("피드 컬렉션 댓글을 삭제 요청 성공하면 응답 코드 204를 반환한다.")
    @Test
    void when_request_to_delete_feed_collection_comment_then_respond_code_204() {
        // docs
        api_문서_타이틀("feed_collection_comment_delete_success", spec);

        // given
        var id = 피드_컬렉션에_댓글을_등록하고_아이디를_받는다(회원아티_액세스토큰, feedCollectionId);

        // when
        var response = 피드_컬렉션에_댓글을_삭제한다(회원아티_액세스토큰, feedCollectionId, id, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(204);
    }

    @DisplayName("피드 컬렉션 댓글을 수정 요청 성공하면 응답 코드 204를 반환한다.")
    @Test
    void when_request_to_update_feed_collection_comment_then_respond_code_204() {
        // docs
        api_문서_타이틀("feed_collection_comment_edit_success", spec);

        // given
        var id = 피드_컬렉션에_댓글을_등록하고_아이디를_받는다(회원아티_액세스토큰, feedCollectionId);

        // when
        var response = 피드_컬렉션에_댓글을_수정한다(회원아티_액세스토큰, feedCollectionId, id, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(204);
    }
}
