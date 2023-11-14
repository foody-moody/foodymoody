package com.foodymoody.be.acceptance.mood;

import static com.foodymoody.be.acceptance.mood.MoodSteps.count_없이_랜덤_무드_3개를_조회한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.page는1_size는3인_무드_슬라이스를_조회한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.랜덤_무드_3개를_조회한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.무드를_조회한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.응답코드가_200이고_3개의_무드가_조회되는지_검증한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.응답코드가_200이고_빈_리스트가_조회되는지_검증한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.응답코드가_200이고_페이지가_0이고_사이즈가_10인_무드_슬라이스가_조회되는지_검증한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.응답코드가_200이고_페이지가_1이고_사이즈가_3인_무드_슬라이스가_조회되는지_검증한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.응답코드가_400이고_예외가_발생하는지_검증한다;


import com.foodymoody.be.acceptance.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("무드 관련 기능 인수 테스트")
public class MoodAcceptanceTest extends AcceptanceTest {

    @Nested
    @DisplayName("무드 랜덤 조회 인수테스트")
    class FetchRandom {

        @DisplayName("무드 랜덤 조회시 무드가 조회되고 200을 응답한다")
        @Test
        void when_countExistsAndInt_then_return200AndMoods() {
            // docs
            api_문서_타이틀("fetchRandomMood_success", spec);

            // when
            var response = 랜덤_무드_3개를_조회한다(spec);

            // then
            응답코드가_200이고_3개의_무드가_조회되는지_검증한다(response);
        }

        @DisplayName("무드 랜덤 조회시 아직 무드가 존재하지 않으면 빈 리스트를 반환하고 200을 응답한다")
        @Test
        void when_moodNotExists_then_return200AndEmptyList() {
            // docs
            api_문서_타이틀("fetchRandomMood_whenMoodNotExists_success", spec);

            //given
            무드_테이블을_비운다();

            // when
            var response = 랜덤_무드_3개를_조회한다(spec);

            // then
            응답코드가_200이고_빈_리스트가_조회되는지_검증한다(response);

            무드_테이블을_초기화한다();
        }

        @DisplayName("무드 랜덤 조회시 쿼리 파라미터에 count가 없으면 예외가 발생하고 400을 응답한다")
        @Test
        void when_countIsNull_then_return400() {
            // docs
            api_문서_타이틀("fetchRandomMood_failedByCountNull", spec);

            // when
            var response = count_없이_랜덤_무드_3개를_조회한다(spec);

            // then
            응답코드가_400이고_예외가_발생하는지_검증한다(response);
        }

    }

    @Nested
    @DisplayName("무드 슬라이스 조회 인수테스트")
    class FetchSlice {

        @DisplayName("무드 슬라이스 조회시 무드가 조회되고 200을 응답한다")
        @Test
        void when_fetchMoods_then_return200AndMoods() {
            // docs
            api_문서_타이틀("fetchSliceMood_success", spec);

            // when
            var response = 무드를_조회한다(spec);

            // then
            응답코드가_200이고_페이지가_0이고_사이즈가_10인_무드_슬라이스가_조회되는지_검증한다(response);
        }

        @DisplayName("page=1 size=3인 무드 슬라이스 조회시 무드가 조회되고 200을 응답한다")
        @Test
        void when_pageIs1AndSizeIs3_then_Return200AndMoods() {
            // docs
            api_문서_타이틀("fetchSliceMood_whenPageAndSizeExists_success", spec);

            // when
            var response = page는1_size는3인_무드_슬라이스를_조회한다(spec);

            // then
            응답코드가_200이고_페이지가_1이고_사이즈가_3인_무드_슬라이스가_조회되는지_검증한다(response);
        }
    }

    private void 무드_테이블을_비운다() {
        테이블을_비운다("mood");
    }

    private void 무드_테이블을_초기화한다() {
        sql파일을_실행한다("mood.sql");
    }
}
