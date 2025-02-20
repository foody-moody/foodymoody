package com.foodymoody.be.acceptance.feed_like;

import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록한다;
import static com.foodymoody.be.acceptance.feed_like.FeedLikeSteps.응답코드가_200이고_id가_존재하면_정상적으로_좋아요_가능;
import static com.foodymoody.be.acceptance.feed_like.FeedLikeSteps.응답코드가_204이면_정상적으로_좋아요_취소;
import static com.foodymoody.be.acceptance.feed_like.FeedLikeSteps.좋아요_취소를_한다;
import static com.foodymoody.be.acceptance.feed_like.FeedLikeSteps.좋아요_한_적이_없는데_좋아요_취소를_한다;
import static com.foodymoody.be.acceptance.feed_like.FeedLikeSteps.좋아요된_피드에_또_좋아요를_한다;
import static com.foodymoody.be.acceptance.feed_like.FeedLikeSteps.좋아요를_한다;
import static com.foodymoody.be.acceptance.image.ImageSteps.피드_이미지를_업로드한다;

import com.foodymoody.be.acceptance.AcceptanceTest;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("피드 좋아요 인수 테스트")
class FeedLikeAcceptanceTest extends AcceptanceTest {

    @AfterEach
    void cleanupDatabase() {
        데이터베이스를_초기화한다();
    }

    @DisplayName("좋아요에 성공하면 응답코드 200을 반환한다.")
    @Test
    void when_like_then_response200() {
        // docs
        api_문서_타이틀("like", spec);

        // given
        List<String> imageIds = 피드_이미지_업로드_후_id_리스트를_반환한다();

        String feedId = 피드를_등록한다(회원아티_액세스토큰, spec, imageIds).jsonPath().getString("id");

        // when
        var response = 좋아요를_한다(feedId, 회원아티_액세스토큰, spec);

        // then
        응답코드가_200이고_id가_존재하면_정상적으로_좋아요_가능(response);
    }

    @DisplayName("이미 좋아요 된 피드라면 테스트에 실패한다.")
    @Test
    void when_then_like_already_exists() {
        // docs
        api_문서_타이틀("likeFailed", spec);

        // given
        List<String> imageIds = 피드_이미지_업로드_후_id_리스트를_반환한다();

        String feedId = 피드를_등록한다(회원아티_액세스토큰, spec, imageIds).jsonPath().getString("id");

        // when
        좋아요를_한다(feedId, 회원아티_액세스토큰, spec);

        // then
        좋아요된_피드에_또_좋아요를_한다(feedId, 회원아티_액세스토큰, spec);
    }

    @DisplayName("좋아요 취소에 성공하면 응답코드 200을 반환한다.")
    @Test
    void when_unLike_then_response200() {
        // docs
        api_문서_타이틀("unLike", spec);

        // given
        List<String> imageIds = 피드_이미지_업로드_후_id_리스트를_반환한다();

        String feedId = 피드를_등록한다(회원푸반_액세스토큰, spec, imageIds).jsonPath().getString("id");

        // when
        좋아요를_한다(feedId, 회원푸반_액세스토큰, spec);
        var response = 좋아요_취소를_한다(feedId, 회원푸반_액세스토큰, spec);

        // then
        응답코드가_204이면_정상적으로_좋아요_취소(response);
    }

    @DisplayName("좋아요 된 피드가 없는데 좋아요 취소를 하면 테스트에 실패한다.")
    @Test
    void when_then_unLike_does_not_exist() {
        // docs
        api_문서_타이틀("unLikeFailed", spec);

        // given
        List<String> imageIds = 피드_이미지_업로드_후_id_리스트를_반환한다();

        String feedId = 피드를_등록한다(회원푸반_액세스토큰, spec, imageIds).jsonPath().getString("id");

        // when
        // then
        좋아요_한_적이_없는데_좋아요_취소를_한다(feedId, 회원푸반_액세스토큰, spec);
    }

    @NotNull
    private List<String> 피드_이미지_업로드_후_id_리스트를_반환한다() {
        var imageResponse1 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
        String id1 = imageResponse1.jsonPath().getString("id");
        var imageResponse2 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
        String id2 = imageResponse2.jsonPath().getString("id");
        return List.of(id1, id2);
    }

}
