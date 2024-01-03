package com.foodymoody.be.acceptance.feed_collection_mood;

import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.피드_컬렉션_등록하고_아이디를_가져온다;
import static com.foodymoody.be.acceptance.feed_collection_mood.FeedCollectionMoodSteps.전체_피드_컬렉션_무드를_조회한다;
import static com.foodymoody.be.acceptance.feed_collection_mood.FeedCollectionMoodSteps.피드_컬렉션_무드를_등록하고_아이디를_가져온다;
import static com.foodymoody.be.acceptance.feed_collection_mood.FeedCollectionMoodSteps.피드_컬렉션_무드를_등록한다;
import static com.foodymoody.be.acceptance.feed_collection_mood.FeedCollectionMoodSteps.피드_컬렉션_무드를_제거한다;
import static com.foodymoody.be.acceptance.feed_collection_mood.FeedCollectionMoodSteps.피드_컬렉션_무드를_추가한다;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("피드 컬렉션의 무드 테스트")
class FeedCollectionMoodAcceptanceTest extends AcceptanceTest {

    @DisplayName("피드 컬렉션의 무드를 생성요청 성공하면 code 201을 반환한다.")
    @Test
    void when_create_feed_collection_mood_if_success_then_return_201() {
        // docs
        api_문서_타이틀("feed_collection_mood_created_success", spec);

        // when
        var response = 피드_컬렉션_무드를_등록한다(회원아티_액세스토큰, spec);

        // then
        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(201),
                () -> assertThat(response.jsonPath().getString("id")).isNotBlank()
        );
    }

    @DisplayName("피드 컬렉션 무드를 조회하면 무드를 조회되고 code 200을 반환한다.")
    @Test
    void when_find_all_feed_collection_mood_if_success_then_return_200() {
        // docs
        api_문서_타이틀("feed_collection_mood_find_all_success", spec);

        // given
        피드_컬렉션_무드를_등록한다(회원아티_액세스토큰);

        // when
        var response = 전체_피드_컬렉션_무드를_조회한다(spec, 회원아티_액세스토큰);

        // then
        int size = response.jsonPath().getList("content").size();
        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(size).isEqualTo(1),
                () -> assertThat(response.jsonPath().getString("content[0].id")).isNotBlank(),
                () -> assertThat(response.jsonPath().getString("content[0].name")).isEqualTo("행복")
        );
    }

    @DisplayName("피드 컬레션에 무드를 추가 요청 성공시 code 204을 반환한다.")
    @Test
    void when_add_feed_collection_mood_if_success_then_return_201() {
        // docs
        api_문서_타이틀("feed_collection_add_mood_success", spec);

        // given
        var feedCollectionId = 피드_컬렉션_등록하고_아이디를_가져온다(Collections.EMPTY_LIST, 회원아티_액세스토큰);
        var moodId = 피드_컬렉션_무드를_등록하고_아이디를_가져온다(회원아티_액세스토큰);

        // when
        var response = 피드_컬렉션_무드를_추가한다(feedCollectionId, moodId, 회원아티_액세스토큰, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(204);
    }

    @DisplayName("피드 컬렉션에 무드를 제거 요청 성공시 code 204을 반환한다.")
    @Test
    void when_remove_feed_collection_mood_if_success_then_return_204() {
        // docs
        api_문서_타이틀("feed_collection_remove_mood_success", spec);

        // given
        var feedCollectionId = 피드_컬렉션_등록하고_아이디를_가져온다(Collections.EMPTY_LIST, 회원아티_액세스토큰);
        var moodId = 피드_컬렉션_무드를_등록하고_아이디를_가져온다(회원아티_액세스토큰);
        피드_컬렉션_무드를_추가한다(feedCollectionId, moodId, 회원아티_액세스토큰);

        // when
        var response = 피드_컬렉션_무드를_제거한다(feedCollectionId, moodId, 회원아티_액세스토큰, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(204);
    }
}
