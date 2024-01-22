package com.foodymoody.be.acceptance.feed;

import static com.foodymoody.be.acceptance.comment.CommentSteps.피드에_댓글을_등록한다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.개별_피드를_조회한다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.바디_없는_피드를_등록한다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.응답코드가_200이고_id가_존재하면_정상적으로_등록된_피드;
import static com.foodymoody.be.acceptance.feed.FeedSteps.응답코드가_200이고_개별_피드가_조회되면_정상적으로_등록된_피드;
import static com.foodymoody.be.acceptance.feed.FeedSteps.응답코드가_200이고_전체_스토어_무드가_조회되면_정상적으로_조회_가능한_전체_스토어_무드;
import static com.foodymoody.be.acceptance.feed.FeedSteps.응답코드가_200이고_전체_피드가_조회되면_정상적으로_조회_가능한_전체_페이지;
import static com.foodymoody.be.acceptance.feed.FeedSteps.응답코드가_204라면_정상적으로_수정_삭제된_피드;
import static com.foodymoody.be.acceptance.feed.FeedSteps.응답코드가_400이다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.전체_스토어_무드를_조회한다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.전체_피드를_조회한다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록한다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_또_등록한다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_삭제한다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_수정한다;
import static com.foodymoody.be.acceptance.image.ImageSteps.피드_이미지를_업로드한다;

import com.foodymoody.be.acceptance.AcceptanceTest;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("피드 인수 테스트")
class FeedAcceptanceTest extends AcceptanceTest {

    @AfterEach
    void cleanupDatabase() {
        데이터베이스를_초기화한다();
    }

    @DisplayName("피드 등록에 성공하면 응답코드 200을 반환한다")
    @Test
    void when_register_feed_then_response200() {
        // docs
        api_문서_타이틀("registerFeed", spec);

        // given
        List<String> imageIds = 피드_이미지_업로드_후_id_리스트를_반환한다();

        // when
        var response = 피드를_등록한다(회원아티_액세스토큰, spec, imageIds);

        // then
        응답코드가_200이고_id가_존재하면_정상적으로_등록된_피드(response);
    }

    @DisplayName("피드 등록 요청 시, 요청 바디가 없으면 응답코드 400을 반환한다")
    @Test
    void when_register_feed_if_request_body_not_exist_then_return_code_400() {
        // docs
        api_문서_타이틀("feed_register_failed_by_request_body_not_exists", spec);

        // given
        List<String> imageIds = 피드_이미지_업로드_후_id_리스트를_반환한다();

        // when
        var response = 바디_없는_피드를_등록한다(회원아티_액세스토큰, spec, imageIds);

        // then
        응답코드가_400이다(response);
    }

    @DisplayName("전체 피드 조회에 성공하면 응답코드 200을 반환한다")
    @Test
    void when_readAll_feed_then_response200() {
        // docs
        api_문서_타이틀("readAllFeed", spec);

        // given
        List<String> imageIds = 피드_이미지_업로드_후_id_리스트를_반환한다();
        var feedResponse = 피드를_등록한다(회원아티_액세스토큰, imageIds);
        String feedId = feedResponse.jsonPath().getString("id");
        피드에_댓글을_등록한다(feedId, 회원푸반_액세스토큰);

        List<String> imageIdsAgain = 피드_이미지_업로드_후_id_리스트를_반환한다();
        var feedResponseAgain = 피드를_또_등록한다(회원푸반_액세스토큰, imageIdsAgain);
        String feedIdAgain = feedResponseAgain.jsonPath().getString("id");
        피드에_댓글을_등록한다(feedIdAgain, 회원푸반_액세스토큰);

        // when
        var readFeedResponse = 전체_피드를_조회한다(spec, 0, 10);

        // then
        응답코드가_200이고_전체_피드가_조회되면_정상적으로_조회_가능한_전체_페이지(readFeedResponse);
    }

    @DisplayName("개별 피드 조회에 성공하면 응답코드 200을 반환한다")
    @Test
    void when_read_feed_then_response200() {
        // docs
        api_문서_타이틀("readFeed", spec);

        // given
        List<String> imageIds = 피드_이미지_업로드_후_id_리스트를_반환한다();
        var response = 피드를_등록한다(회원아티_액세스토큰, imageIds);
        String registeredId = response.jsonPath().getString("id");
        피드에_댓글을_등록한다(registeredId, 회원푸반_액세스토큰);

        // when
        var readFeedResponse = 개별_피드를_조회한다(registeredId, spec);

        // then
        응답코드가_200이고_개별_피드가_조회되면_정상적으로_등록된_피드(readFeedResponse);
    }

    @DisplayName("피드 수정에 성공하면 응답코드 204를 반환한다.")
    @Test
    void when_update_feed_then_response204() {
        // docs
        api_문서_타이틀("updateFeed", spec);

        // given
        List<String> imageIds = 피드_이미지_업로드_후_id_리스트를_반환한다();
        var response = 피드를_등록한다(회원아티_액세스토큰, imageIds);
        String registeredId = response.jsonPath().getString("id");

        // when
        var updateResponse = 피드를_수정한다(회원아티_액세스토큰, registeredId, spec);

        // then
        응답코드가_204라면_정상적으로_수정_삭제된_피드(updateResponse);
    }

    @DisplayName("피드 삭제에 성공하면 응답코드 204를 반환한다.")
    @Test
    void when_delete_feed_then_response204() {
        // docs
        api_문서_타이틀("deleteFeed", spec);

        // given
        List<String> imageIds = 피드_이미지_업로드_후_id_리스트를_반환한다();
        var response = 피드를_등록한다(회원아티_액세스토큰, imageIds);
        String registeredId = response.jsonPath().getString("id");

        // when
        var deleteResponse = 피드를_삭제한다(회원아티_액세스토큰, registeredId, spec);

        // then
        응답코드가_204라면_정상적으로_수정_삭제된_피드(deleteResponse);
    }

    @DisplayName("전체 스토어 무드 조회에 성공하면 응답코드 200을 반환한다.")
    @Test
    void when_readAll_store_mood_then_response200() {
        // docs
        api_문서_타이틀("readAllStoreMood", spec);

        // when
        var response = 전체_스토어_무드를_조회한다(spec);

        // then
        응답코드가_200이고_전체_스토어_무드가_조회되면_정상적으로_조회_가능한_전체_스토어_무드(response);
    }

    public List<String> 피드_이미지_업로드_후_id_리스트를_반환한다() {
        var imageResponse1 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
        String id1 = imageResponse1.jsonPath().getString("id");
        var imageResponse2 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
        String id2 = imageResponse2.jsonPath().getString("id");
        return List.of(id1, id2);
    }

}
