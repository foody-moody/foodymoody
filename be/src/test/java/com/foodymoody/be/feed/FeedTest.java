package com.foodymoody.be.feed;

import static com.foodymoody.be.feed.FeedSteps.개별_피드를_조회한다;
import static com.foodymoody.be.feed.FeedSteps.응답코드가_200이고_id가_존재하면_정상적으로_등록된_피드;
import static com.foodymoody.be.feed.FeedSteps.응답코드가_200이고_개별_피드가_조회되면_정상적으로_등록된_피드;
import static com.foodymoody.be.feed.FeedSteps.응답코드가_200이고_전체_피드가_조회되면_정상적으로_조회_가능한_전체_페이지;
import static com.foodymoody.be.feed.FeedSteps.응답코드가_204라면_정상적으로_수정_삭제된_피드;
import static com.foodymoody.be.feed.FeedSteps.전체_피드를_조회한다;
import static com.foodymoody.be.feed.FeedSteps.피드를_등록한다;
import static com.foodymoody.be.feed.FeedSteps.피드를_삭제한다;
import static com.foodymoody.be.feed.FeedSteps.피드를_수정한다;

import com.foodymoody.be.docs.Document;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedTest extends Document {

    @DisplayName("전체 피드 조회에 성공하면 응답코드 200을 반환한다")
    @Test
    void when_readAllFeed_then_response200() {
        // docs
        api_문서_타이틀("readAllFeed", spec);

        // given
        var registerResponse = 피드를_등록한다(new RequestSpecBuilder().build());
        long registeredId = registerResponse.jsonPath().getLong("id");

        // when
        var readFeedResponse = 전체_피드를_조회한다(spec, 0 , 10);

        // then
        응답코드가_200이고_전체_피드가_조회되면_정상적으로_조회_가능한_전체_페이지(readFeedResponse);
    }

    @DisplayName("피드 등록에 성공하면 응답코드 200을 반환한다")
    @Test
    void when_registerFeed_then_response200() {
        // docs
        api_문서_타이틀("registerFeed", spec);

        // given,when
        var response = 피드를_등록한다(new RequestSpecBuilder().build());

        // then
        응답코드가_200이고_id가_존재하면_정상적으로_등록된_피드(response);
    }

    @DisplayName("개별 피드 조회에 성공하면 응답코드 200을 반환한다")
    @Test
    void when_readFeed_then_response200() {
        // docs
        api_문서_타이틀("readFeed", spec);

        // given
        var registerResponse = 피드를_등록한다(new RequestSpecBuilder().build());
        long registeredId = registerResponse.jsonPath().getLong("id");

        // when
        var readFeedResponse = 개별_피드를_조회한다(registeredId, spec);

        // then
        응답코드가_200이고_개별_피드가_조회되면_정상적으로_등록된_피드(readFeedResponse);
    }

    @DisplayName("피드 수정에 성공하면 응답코드 204를 반환한다.")
    @Test
    void when_updateFeed_then_response204() {
        // docs
        api_문서_타이틀("updateFeed", spec);

        // given
        var registerResponse = 피드를_등록한다(new RequestSpecBuilder().build());
        long registeredId = registerResponse.jsonPath().getLong("id");

        // when
        var updateResponse = 피드를_수정한다(registeredId, spec);

        // then
        응답코드가_204라면_정상적으로_수정_삭제된_피드(updateResponse);
    }

    @DisplayName("피드 삭제에 성공하면 응답코드 204를 반환한다.")
    @Test
    void when_deleteFeed_then_response204() {
        // docs
        api_문서_타이틀("deleteFeed", spec);

        // given
        var registerResponse = 피드를_등록한다(new RequestSpecBuilder().build());
        long registeredId = registerResponse.jsonPath().getLong("id");

        // when
        var deleteResponse = 피드를_삭제한다(registeredId, spec);

        // then
        응답코드가_204라면_정상적으로_수정_삭제된_피드(deleteResponse);
    }

}
