//package com.foodymoody.be.acceptance.store_like;
//
//import static com.foodymoody.be.acceptance.store.StoreSteps.가게_상세정보를_조회한다;
//import static com.foodymoody.be.acceptance.store_like.StoreLikeSteps.가게_좋아요를_등록한다;
//import static com.foodymoody.be.acceptance.store_like.StoreLikeSteps.가게_좋아요를_취소한다;
//import static com.foodymoody.be.acceptance.store_like.StoreLikeSteps.상태코드를_검증한다;
//import static com.foodymoody.be.acceptance.store_like.StoreLikeSteps.오류코드를_검증한다;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.foodymoody.be.acceptance.AcceptanceTest;
//import io.restassured.builder.RequestSpecBuilder;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpStatus;
//
//@DisplayName("가게 좋아요 기능 인수테스트")
//public class StoreLikeAcceptanceTest extends AcceptanceTest {
//
//    @Nested
//    @DisplayName("좋아요 등록")
//    class Register {
//
//        @Test
//        void when_register_store_like_if_success_then_response_status_code_201_and_like_registered() {
//            // docs
//            api_문서_타이틀("register_store_like_if_success", spec);
//
//            // when
//            var response = 가게_좋아요를_등록한다(회원푸반_액세스토큰, "1", spec);
//
//            // then
//            var 가게_상세정보조회_응답 = 가게_상세정보를_조회한다(회원푸반_액세스토큰, "1", new RequestSpecBuilder().build());
//
//            Assertions.assertAll(
//                    () -> 상태코드를_검증한다(response, HttpStatus.CREATED),
//                    () -> assertThat(가게_상세정보조회_응답.jsonPath().getLong("likeCount")).isEqualTo(1),
//                    () -> assertThat(가게_상세정보조회_응답.jsonPath().getBoolean("liked")).isTrue()
//            );
//        }
//
//        @Test
//        void when_register_store_like_if_failed_by_already_liked_then_response_status_code_400_and_error_code_s002() {
//            // docs
//            api_문서_타이틀("register_store_like_if_failed_by_already_liked", spec);
//
//            // given
//            가게_좋아요를_등록한다(회원푸반_액세스토큰, "1");
//
//            // when
//            var response = 가게_좋아요를_등록한다(회원푸반_액세스토큰, "1", spec);
//
//            // then
//            var 가게_상세정보조회_응답 = 가게_상세정보를_조회한다(회원푸반_액세스토큰, "1", new RequestSpecBuilder().build());
//
//            Assertions.assertAll(
//                    () -> 상태코드를_검증한다(response, HttpStatus.BAD_REQUEST),
//                    () -> 오류코드를_검증한다(response, "s002"),
//                    () -> assertThat(가게_상세정보조회_응답.jsonPath().getLong("likeCount")).isEqualTo(1),
//                    () -> assertThat(가게_상세정보조회_응답.jsonPath().getBoolean("liked")).isTrue()
//            );
//        }
//
//    }
//
//    @Nested
//    @DisplayName("좋아요 취소")
//    class Cancel {
//
//        @Test
//        void when_cancel_store_like_if_success_then_response_status_code_200_and_like_canceled() {
//            // docs
//            api_문서_타이틀("cancel_store_like_if_success", spec);
//
//            // given
//            가게_좋아요를_등록한다(회원푸반_액세스토큰, "1");
//
//            // when
//            var response = 가게_좋아요를_취소한다(회원푸반_액세스토큰, "1", spec);
//
//            // then
//            var 가게_상세정보조회_응답 = 가게_상세정보를_조회한다(회원푸반_액세스토큰, "1", new RequestSpecBuilder().build());
//
//            Assertions.assertAll(
//                    () -> 상태코드를_검증한다(response, HttpStatus.NO_CONTENT),
//                    () -> assertThat(가게_상세정보조회_응답.jsonPath().getLong("likeCount")).isZero(),
//                    () -> assertThat(가게_상세정보조회_응답.jsonPath().getBoolean("liked")).isFalse()
//            );
//        }
//
//        @Test
//        void when_cancel_store_like_if_failed_by_not_liked_then_response_status_code_400_and_error_code_s003() {
//            // docs
//            api_문서_타이틀("register_store_like_if_failed_by_already_liked", spec);
//
//            // when
//            var response = 가게_좋아요를_취소한다(회원푸반_액세스토큰, "1", spec);
//
//            // then
//            var 가게_상세정보조회_응답 = 가게_상세정보를_조회한다(회원푸반_액세스토큰, "1", new RequestSpecBuilder().build());
//
//            Assertions.assertAll(
//                    () -> 상태코드를_검증한다(response, HttpStatus.BAD_REQUEST),
//                    () -> 오류코드를_검증한다(response, "s003"),
//                    () -> assertThat(가게_상세정보조회_응답.jsonPath().getLong("likeCount")).isZero()
//            );
//        }
//
//    }
//
//}
