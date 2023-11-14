package com.foodymoody.be.acceptance.mood;

import static com.foodymoody.be.acceptance.mood.MoodSteps.count_없이_랜덤_무드_3개를_조회한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.null인_무드를_추가한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.page는1_size는3인_무드_슬라이스를_조회한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.랜덤_무드_3개를_조회한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.무드를_조회한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.베지테리언_무드를_id로_조회한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.베지테리언_무드를_추가한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.얼죽아_무드를_추가한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.응답코드가_200이고_3개의_무드가_조회되는지_검증한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.응답코드가_200이고_베지테리언이_조회되는지_검증한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.응답코드가_200이고_빈_리스트가_조회되는지_검증한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.응답코드가_200이고_추가된_무드의_id에_해당하는_무드가_조회되는지_검증한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.응답코드가_200이고_페이지가_0이고_사이즈가_10인_무드_슬라이스가_조회되는지_검증한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.응답코드가_200이고_페이지가_1이고_사이즈가_3인_무드_슬라이스가_조회되는지_검증한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.응답코드가_400이고_예외가_발생하는지_검증한다;
import static com.foodymoody.be.acceptance.mood.MoodSteps.응답코드가_400임을_검증한다;

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

    @Nested
    @DisplayName("무드 등록 인수테스트")
    class Register {
        @DisplayName("무드 등록에 성공하고 200을 반환한다")
        @Test
        void registerMood_success() {
            // docs
            api_문서_타이틀("registerMood_success", spec);

            // when
            var response = 얼죽아_무드를_추가한다(spec);

            // then
            응답코드가_200이고_추가된_무드의_id에_해당하는_무드가_조회되는지_검증한다(response);
        }

        @DisplayName("이미 존재하는 무드이면 400을 반환한다")
        @Test
        void when_duplicateMood_then_return400() {
            // docs
            api_문서_타이틀("registerMood_failedByDuplicateName", spec);

            // when
            var response = 베지테리언_무드를_추가한다(spec);

            // then
            응답코드가_400임을_검증한다(response);
        }

        @DisplayName("null이면 400을 반환한다")
        @Test
        void when_NullMood_then_return400() {
            // docs
            api_문서_타이틀("registerMood_failedByNullName", spec);

            // when
            var response = null인_무드를_추가한다(spec);

            // then
            응답코드가_400임을_검증한다(response);
        }
    }

    @Nested
    @DisplayName("id로 무드 조회 인수테스트")
    class FindById {
        @DisplayName("무드 조회에 성공하고 200을 반환한다")
        @Test
        void findMoodById_success() {
            // docs
            api_문서_타이틀("findMoodyById_success", spec);

            // when
            var response = 베지테리언_무드를_id로_조회한다(spec);

            // then
            응답코드가_200이고_베지테리언이_조회되는지_검증한다(response);
        }

    }

//    TODO
//    @Nested
//    @DisplayName("name으로 무드 조회 인수테스트")
//    class FindByName {
//        @DisplayName("무드 조회에 성공하고 200을 반환한다")
//        @Test
//        void registerMood_success() {
//            // docs
//            api_문서_타이틀("findMoodByName_success", new RequestSpecBuilder().build());
//
//            // when
//            var response = 베지테리언_무드를_이름으로_검색한다(new RequestSpecBuilder().build());
//
//            // then
//            응답코드가_200이고_베지테리언이_검색되는지_검증한다(response);
//        }
//    }

    private void 무드_테이블을_비운다() {
        테이블을_비운다("mood");
    }

    private void 무드_테이블을_초기화한다() {
        sql파일을_실행한다("mood.sql");
    }
}
