package com.foodymoody.be.acceptance.store;

import static com.foodymoody.be.acceptance.store.StoreSteps.상태코드를_검증한다;
import static com.foodymoody.be.acceptance.store.StoreSteps.식당_상세정보를_조회한다;
import static com.foodymoody.be.acceptance.store.StoreSteps.식당을_검색한다;
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

            // when
            var response = 식당_상세정보를_조회한다(spec, "2");

            // then
            Assertions.assertAll(
                    () -> 상태코드를_검증한다(response, HttpStatus.OK),
                    () -> assertThat(response.jsonPath().getString("name")).isEqualTo("폐업한 식당"),
                    () -> assertThat(response.jsonPath().getBoolean("closed")).isTrue()
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
            var response = 식당을_검색한다(spec, "송파구");

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
            var response = 식당을_검색한다(spec, "폐업한 식당");

            // then
            Assertions.assertAll(
                    () -> 상태코드를_검증한다(response, HttpStatus.OK),
                    () -> assertThat(response.jsonPath().getList("")).isEmpty()
            );

        }
    }

}
