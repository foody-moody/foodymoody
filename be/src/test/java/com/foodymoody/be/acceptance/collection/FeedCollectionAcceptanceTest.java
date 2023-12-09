package com.foodymoody.be.acceptance.collection;

import static com.foodymoody.be.acceptance.collection.FeedCollectionStep.컬렉션_등록한다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록하고_아이디를_받는다;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("컬렉션 관련 기능")
class FeedCollectionAcceptanceTest extends AcceptanceTest {

    List<String> feedIds;

    @BeforeEach
    void setUp() {
        feedIds = new ArrayList<>();
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰));
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰));
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰));
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰));
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰));
    }

    @DisplayName("컬렉션을 생성요청 성공하면 201을 반환한다.")
    @Test
    void when_create_collection_if_success_then_return_code_201() {
        // restdocs
        api_문서_타이틀("feed-collection-create-success", spec);

        // when
        var response = 컬렉션_등록한다(spec, 회원아티_액세스토큰, feedIds);

        // then
        assertThat(response.statusCode()).isEqualTo(201);
    }
}
