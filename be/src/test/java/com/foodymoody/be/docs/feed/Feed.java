package com.foodymoody.be.docs.feed;

import static com.foodymoody.be.docs.feed.FeedSteps.응답코드가_200이고_id가_1이면_정상적으로_등록된_피드;
import static com.foodymoody.be.docs.feed.FeedSteps.피드를_등록한다;

import com.foodymoody.be.docs.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Feed extends Document {

    @DisplayName("피드를 등록 성공하면 응답코드 200을 반환한다")
    @Test
    void when_registerFeed_then_response200() {
        // docs
        api_문서_타이틀("registerFeed", spec);

        // given,when
        var response = 피드를_등록한다(spec);

        // then
        응답코드가_200이고_id가_1이면_정상적으로_등록된_피드(response);
    }
}
