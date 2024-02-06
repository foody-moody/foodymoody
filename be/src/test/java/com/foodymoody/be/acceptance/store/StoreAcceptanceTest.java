package com.foodymoody.be.acceptance.store;

import static com.foodymoody.be.acceptance.store.StoreSteps.가게별_피드를_조회한다;
import static com.foodymoody.be.acceptance.store.StoreSteps.상태코드를_검증한다;
import static com.foodymoody.be.acceptance.store.StoreSteps.가게_상세정보를_조회한다;
import static com.foodymoody.be.acceptance.store.StoreSteps.가게를_검색한다;
import static com.foodymoody.be.acceptance.store.StoreSteps.이미지를_업로드하고_특정_id의_가게에_대한_피드를_등록한다;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@DisplayName("식당 인수 테스트")
public class StoreAcceptanceTest extends AcceptanceTest {

    @DisplayName("식당 상세정보 조회")
    @Nested
    class FetchDetails {

        @Test
        void when_fetch_details_if_success_then_response_status_code_200_and_details() {
            // docs
            api_문서_타이틀("fetch_store_details_success", spec);
            이미지를_업로드하고_특정_id의_가게에_대한_피드를_등록한다(회원푸반_액세스토큰, "2");
            이미지를_업로드하고_특정_id의_가게에_대한_피드를_등록한다(회원푸반_액세스토큰, "2");
            이미지를_업로드하고_특정_id의_가게에_대한_피드를_등록한다(회원푸반_액세스토큰, "2");
            이미지를_업로드하고_특정_id의_가게에_대한_피드를_등록한다(회원푸반_액세스토큰, "1");
            이미지를_업로드하고_특정_id의_가게에_대한_피드를_등록한다(회원푸반_액세스토큰, "3");

            // when
            var response = 가게_상세정보를_조회한다("2", spec);

            // then
            Assertions.assertAll(
                    () -> 상태코드를_검증한다(response, HttpStatus.OK),
                    () -> assertThat(response.jsonPath().getString("name")).isEqualTo("폐업한 식당"),
                    () -> assertThat(response.jsonPath().getBoolean("closed")).isTrue(),
                    () -> assertThat(response.jsonPath().getLong("feedCount")).isEqualTo(3L),
                    () -> assertThat(response.jsonPath().getDouble("rating")).isEqualTo(2.5)
            );
        }

        @Test
        void when_fetch_details_if_success_and_no_feed_then_response_status_code_200_and_details() {
            // docs
            api_문서_타이틀("fetch_store_details_success", spec);
            이미지를_업로드하고_특정_id의_가게에_대한_피드를_등록한다(회원푸반_액세스토큰, "1");
            이미지를_업로드하고_특정_id의_가게에_대한_피드를_등록한다(회원푸반_액세스토큰, "3");

            // when
            var response = 가게_상세정보를_조회한다("2", spec);

            // then
            Assertions.assertAll(
                    () -> 상태코드를_검증한다(response, HttpStatus.OK),
                    () -> assertThat(response.jsonPath().getString("name")).isEqualTo("폐업한 식당"),
                    () -> assertThat(response.jsonPath().getBoolean("closed")).isTrue(),
                    () -> assertThat(response.jsonPath().getLong("feedCount")).isZero(),
                    () -> assertThat(response.jsonPath().getDouble("rating")).isEqualTo(0.0)
            );
        }
    }

    @DisplayName("식당 검색")
    @Nested
    class Search {

        @Test
        void when_search_if_success_then_response_status_code_200_and_results() {
            // docs
            api_문서_타이틀("search_store_success", spec);

            // when
            var response = 가게를_검색한다( "송파구", spec);

            // then
            Assertions.assertAll(
                    () -> 상태코드를_검증한다(response, HttpStatus.OK),
                    () -> assertThat(response.jsonPath().getList("")).hasSize(15)
            );

        }

        @Test
        void when_search_if_success_and_no_result_then_response_status_code_200_and_empty_list() {
            // docs
            api_문서_타이틀("search_store_success_and_no_result", spec);

            // when
            var response = 가게를_검색한다("폐업한 식당", spec);

            // then
            Assertions.assertAll(
                    () -> 상태코드를_검증한다(response, HttpStatus.OK),
                    () -> assertThat(response.jsonPath().getList("")).isEmpty()
            );

        }

    }

    @DisplayName("가게별 피드 조회")
    @Nested
    class FetchStoreFeed {

        @Test
        void when_fetch_store_feed_if_success_then_response_status_code_200_and_details() {
            // docs
            api_문서_타이틀("fetch_store_feeds_success", spec);
            이미지를_업로드하고_특정_id의_가게에_대한_피드를_등록한다(회원푸반_액세스토큰, "2");
            이미지를_업로드하고_특정_id의_가게에_대한_피드를_등록한다(회원푸반_액세스토큰, "2");
            이미지를_업로드하고_특정_id의_가게에_대한_피드를_등록한다(회원푸반_액세스토큰, "2");
            이미지를_업로드하고_특정_id의_가게에_대한_피드를_등록한다(회원푸반_액세스토큰, "1");
            이미지를_업로드하고_특정_id의_가게에_대한_피드를_등록한다(회원푸반_액세스토큰, "3");

            // when
            var response = 가게별_피드를_조회한다("2", spec);

            // then
            Assertions.assertAll(
                    () -> 상태코드를_검증한다(response, HttpStatus.OK),
                    () -> assertThat(response.jsonPath().getList("content"))
                            .hasSize(3)
            );
        }

    }

}
