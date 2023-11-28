package com.foodymoody.be.acceptance.heart;

import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록한다;
import static com.foodymoody.be.acceptance.heart.HeartSteps.응답코드가_200이고_id가_존재하면_정상적으로_좋아요_가능;
import static com.foodymoody.be.acceptance.heart.HeartSteps.좋아요를_한다;

import com.foodymoody.be.acceptance.AcceptanceTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HeartAcceptanceTest extends AcceptanceTest {

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
        String feedId = 피드를_등록한다(회원아티_액세스토큰, spec).jsonPath().getString("id");

        // when
        var response = 좋아요를_한다(feedId, 회원아티_액세스토큰, spec);

        // then
        응답코드가_200이고_id가_존재하면_정상적으로_좋아요_가능(response);
    }

}
