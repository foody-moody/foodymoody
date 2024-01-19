package com.foodymoody.be.acceptance.reply;

import static com.foodymoody.be.acceptance.comment.CommentSteps.응답코드_200을_반환한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.응답코드_201을_반환한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.피드에_댓글을_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.image.ImageSteps.피드_이미지를_업로드한다;
import static com.foodymoody.be.acceptance.reply.ReplySteps.댓글에_댓글을_등록하고_아이디를_가져온다;
import static com.foodymoody.be.acceptance.reply.ReplySteps.댓글에_댓글을_등록한다;
import static com.foodymoody.be.acceptance.reply.ReplySteps.댓글의_댓글을_조회한다;
import static com.foodymoody.be.acceptance.reply_heart.ReplyHeartSteps.대댓글에_좋아요를_추가한다;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("댓글 인수 테스트")
class FeedReplyAcceptanceTest extends AcceptanceTest {

    String feedId;
    String commentId;

    @BeforeEach
    void setUp() {
        var imageResponse1 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
        String id1 = imageResponse1.jsonPath().getString("id");
        var imageResponse2 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
        String id2 = imageResponse2.jsonPath().getString("id");
        List<String> imageIds = List.of(id1, id2);

        feedId = 피드를_등록하고_아이디를_받는다(회원아티_액세스토큰, imageIds);
        commentId = 피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원아티_액세스토큰);

    }

    @DisplayName("댓글에 댓글 추가하면 응답코드 201을 응답한다")
    @Test
    void when_register_comment_to_comment_if_success_then_return_code_201() {
        // docs
        api_문서_타이틀("comment_reply_success", spec);

        // when
        var response = 댓글에_댓글을_등록한다(feedId, commentId, 회원아티_액세스토큰, spec);

        // then
        응답코드_201을_반환한다(response);
    }

    @DisplayName("미로그인 상태에서 댓글의 댓글을 조회하면 응답코드 200을 응답한다")
    @Test
    void when_fetch_comment_of_comment_if_success_then_return_code_200() {
        // docs
        api_문서_타이틀("comment_reply_fetch_success", spec);

        // given
        댓글에_댓글을_등록한다(feedId, commentId, 회원아티_액세스토큰);
        댓글에_댓글을_등록한다(feedId, commentId, 회원푸반_액세스토큰);
        댓글에_댓글을_등록한다(feedId, commentId, 회원아티_액세스토큰);

        // when
        var response = 댓글의_댓글을_조회한다(commentId, spec);

        // then
        응답코드_200을_반환한다(response);
    }

    @DisplayName("로그인 상태에서 댓글의 댓글을 조회하면 응답코드 200을 응답한다")
    @Test
    void when_fetch_comment_of_comment_with_login_if_success_then_return_code_200() {
        // docs
        api_문서_타이틀("comment_reply_fetch_with_login_success", spec);

        // given
        댓글에_댓글을_등록한다(feedId, commentId, 회원아티_액세스토큰);
        댓글에_댓글을_등록한다(feedId, commentId, 회원푸반_액세스토큰);
        String replyId = 댓글에_댓글을_등록하고_아이디를_가져온다(feedId, commentId, 회원아티_액세스토큰);
        대댓글에_좋아요를_추가한다(회원아티_액세스토큰, commentId, replyId);

        // when
        var response = 댓글의_댓글을_조회한다(commentId, 회원아티_액세스토큰, spec);

        // then
        응답코드_200을_반환한다(response);
        List<Boolean> list = response.body()
                .jsonPath()
                .getList("content.liked", Boolean.class);
        assertThat(list).contains(true);
    }
}
