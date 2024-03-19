package com.foodymoody.be.acceptance.feed_collection;

import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.개별_피드_컬렉션_조회한다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.전체_피드_컬렉션_조회한다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.컬렉션_커멘트를_조회한다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.타이틀_없이_피드_컬렉션_등록한다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.타이틀과_공개여부만으로_피드_컬렉션_등록한다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.피드_컬렉션_등록하고_아이디를_가져온다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.피드_컬렉션_등록하고_피드_리스트도_추가한다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.피드_컬렉션_등록한다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.피드_컬렉션_피드리스트_및_썸네일을_수정한다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.피드_컬렉션_피드리스트를_수정한다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.피드_컬렉션_피드리스트를_조회한다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.피드_컬렉션에_피드를_삭제한다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.피드_컬렉션에_피드를_추가한다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.피드_컬렉션을_삭제한다;
import static com.foodymoody.be.acceptance.feed_collection.FeedCollectionSteps.피드_컬렉션을_수정한다;
import static com.foodymoody.be.acceptance.feed_collection_comment.FeedCollectionCommentSteps.피드_컬렉션에_댓글을_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed_collection_comment.FeedCollectionCommentSteps.피드_컬렉션에_댓글을_등록한다;
import static com.foodymoody.be.acceptance.feed_collection_comment_like.FeedCollectionCommentLikeSteps.피드_컬렉션_댓글에_좋아요를_등록한다;
import static com.foodymoody.be.acceptance.feed_collection_mood.FeedCollectionMoodSteps.피드_컬렉션_무드를_등록하고_아이디를_가져온다;
import static com.foodymoody.be.acceptance.feed_collection_reply.FeedCollectionReplySteps.피드_컬렉션_댓글에_대댓글을_등록한다;
import static com.foodymoody.be.acceptance.image.ImageSteps.피드_이미지를_업로드한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.팔로우한다;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import com.foodymoody.be.auth.infra.JwtUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@DisplayName("피드 컬렉션 인수 테스트")
class FeedCollectionAcceptanceTest extends AcceptanceTest {

    @Autowired
    JwtUtil jwtUtil;
    List<String> feedIds;
    List<String> moodIds;
    String 회원푸반_아이디;

    @BeforeEach
    void setUp() {
        var imageResponse1 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
        String id1 = imageResponse1.jsonPath().getString("id");
        var imageResponse2 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
        String id2 = imageResponse2.jsonPath().getString("id");
        List<String> imageIds = List.of(id1, id2);

        feedIds = new ArrayList<>();
        moodIds = new ArrayList<>();
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰, imageIds));
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰, imageIds));
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰, imageIds));
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰, imageIds));
        feedIds.add(피드를_등록하고_아이디를_받는다(회원아티_액세스토큰, imageIds));
        moodIds.add(피드_컬렉션_무드를_등록하고_아이디를_가져온다(회원아티_액세스토큰));
        moodIds.add(피드_컬렉션_무드를_등록하고_아이디를_가져온다(회원아티_액세스토큰));
        moodIds.add(피드_컬렉션_무드를_등록하고_아이디를_가져온다(회원아티_액세스토큰));
        회원푸반_아이디 = jwtUtil.parseAccessToken(회원푸반_액세스토큰).get("id");
    }

    @DisplayName("피드 컬렉션을 생성 요청이 성공하면 201을 반환한다.")
    @Test
    void when_request_create_collection_if_success_then_return_code_201() {
        // docs
        api_문서_타이틀("feed_collection_request_create_success", spec);

        // when
        var response = 피드_컬렉션_등록한다(moodIds, 회원아티_액세스토큰, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(201);
    }

    @DisplayName("피드 컬렉션을 생성 요청시 타이틀이랑 공개여부만 있으면 성공하고 201을 반환한다.")
    @Test
    void when_request_create_collection_with_only_title_and_public_then_return_code_201() {
        // docs
        api_문서_타이틀("feed_collection_request_create_only_title_and_public_success", spec);

        // when
        var response = 타이틀과_공개여부만으로_피드_컬렉션_등록한다(회원아티_액세스토큰, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(201);
    }

    @DisplayName("피드 컬렉션을 생성 요청시 타이틀이 없으면 실패하고 400을 반환한다.")
    @Test
    void when_request_create_collection_without_title_then_return_code_400() {
        // docs
        api_문서_타이틀("feed_collection_request_create_without_title_fail", spec);

        // when
        var response = 타이틀_없이_피드_컬렉션_등록한다(회원아티_액세스토큰, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(400);
    }

    @DisplayName("피드 컬렉션을 전체 조회 성공하면 200을 반환한다.")
    @Test
    void when_request_fetch_all_collection_if_success_then_return_code_200() {
        // docs
        api_문서_타이틀("feed_collection_request_fetch_all_success", spec);

        // given
        피드_컬렉션_등록하고_피드_리스트도_추가한다(moodIds, 회원아티_액세스토큰, feedIds);
        피드_컬렉션_등록하고_피드_리스트도_추가한다(moodIds, 회원아티_액세스토큰, feedIds);
        피드_컬렉션_등록하고_피드_리스트도_추가한다(moodIds, 회원아티_액세스토큰, feedIds);
        타이틀과_공개여부만으로_피드_컬렉션_등록한다(회원아티_액세스토큰, spec);

        // when
        var response = 전체_피드_컬렉션_조회한다(spec, 회원아티_액세스토큰);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
    }

    @DisplayName("피드 컬렉션을 조회 성공하면 200을 반환한다.")
    @Test
    void when_request_fetch_collection_if_success_then_return_code_200() {
        // docs
        api_문서_타이틀("feed_collection_request_fetch_single_success", spec);

        // given
        var feedCollectionId = 피드_컬렉션_등록하고_피드_리스트도_추가한다(moodIds, 회원아티_액세스토큰, feedIds);
        피드_컬렉션에_댓글을_등록한다(회원푸반_액세스토큰, feedCollectionId);
        피드_컬렉션에_댓글을_등록한다(회원푸반_액세스토큰, feedCollectionId);
        String commentId = 피드_컬렉션에_댓글을_등록하고_아이디를_받는다(회원푸반_액세스토큰, feedCollectionId);
        피드_컬렉션_댓글에_좋아요를_등록한다(feedCollectionId, commentId, 회원아티_액세스토큰);
        피드_컬렉션_댓글에_대댓글을_등록한다(commentId, feedCollectionId, 회원아티_액세스토큰);

        // when
        var response = 개별_피드_컬렉션_조회한다(feedCollectionId, spec, 회원아티_액세스토큰);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
    }

    @DisplayName("피드 컬렉션 작성자를 팔로운 상태에서 피드 컬렉션을 조회 성공하면 200을 반환한다.")
    @Test
    void when_request_fetch_followed_collection_if_success_then_return_code_200() {
        // docs
        api_문서_타이틀("feed_collection_request_fetch_single_success", spec);

        // given
        팔로우한다(회원아티_액세스토큰, 회원푸반_아이디);
        var feedCollectionId = 피드_컬렉션_등록하고_피드_리스트도_추가한다(moodIds, 회원아티_액세스토큰, feedIds);
        피드_컬렉션에_댓글을_등록한다(회원푸반_액세스토큰, feedCollectionId);
        피드_컬렉션에_댓글을_등록한다(회원푸반_액세스토큰, feedCollectionId);

        String commentId = 피드_컬렉션에_댓글을_등록하고_아이디를_받는다(회원푸반_액세스토큰, feedCollectionId);
        피드_컬렉션_댓글에_좋아요를_등록한다(feedCollectionId, commentId, 회원아티_액세스토큰);
        피드_컬렉션_댓글에_대댓글을_등록한다(commentId, feedCollectionId, 회원아티_액세스토큰);

        // when
        var response = 개별_피드_컬렉션_조회한다(feedCollectionId, spec, 회원아티_액세스토큰);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
    }

    @DisplayName("개별 피드 컬렉션의 댓글을 조회 성공하면 200을 반환한다.")
    @Test
    void when_request_fetch_collection_comments_if_success_then_return_code_200() {
        // docs
        api_문서_타이틀("feed_collection_request_fetch_comments_success", spec);

        // given
        var collectionId = 피드_컬렉션_등록하고_피드_리스트도_추가한다(moodIds, 회원아티_액세스토큰, feedIds);
        for (int i = 0; i < 30; i++) {
            피드_컬렉션에_댓글을_등록한다(회원푸반_액세스토큰, collectionId);
        }

        // when
        var response = 컬렉션_커멘트를_조회한다(collectionId, spec, 회원아티_액세스토큰, 1, 10);

        // then
        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.jsonPath().getList("content")).hasSize(10),
                () -> assertThat(response.jsonPath().getBoolean("last")).isFalse(),
                () -> assertThat(response.jsonPath().getBoolean("first")).isFalse()
        );
    }

    @DisplayName("피드 컬렉션을 수정 요청 성공하면 응답 코드 204를 반환한다.")
    @Test
    void when_request_to_update_feed_collection_then_respond_code_204() {
        // docs
        api_문서_타이틀("feed_collection_request_update_success", spec);

        // given
        var collectionId = 피드_컬렉션_등록하고_아이디를_가져온다(moodIds, 회원아티_액세스토큰);
        moodIds.remove(0);

        // when
        var response = 피드_컬렉션을_수정한다(collectionId, 회원아티_액세스토큰, moodIds, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(204);
    }

    @DisplayName("피드 컬렉션 수정 요청 성공하면 응답 코드 204를 반환한다.")
    @Test
    void when_request_to_update_feed_collection_with_thumbnail_url_then_respond_code_204() {
        // docs
        api_문서_타이틀("feed_collection_request_update_feed_list_and_thumbnail_success", spec);

        // given
        String collectionId = 피드_컬렉션_등록하고_피드_리스트도_추가한다(moodIds, 회원아티_액세스토큰, feedIds);
        Collections.reverse(feedIds);

        // when
        var response = 피드_컬렉션_피드리스트_및_썸네일을_수정한다(collectionId, 회원아티_액세스토큰, feedIds, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(204);
    }

    @DisplayName("Feed Collection의 Feed List 조회에 성공하면 응답 코드 200을 반환한다.")
    @Test
    void when_request_to_read_feed_list_then_response_code_200() {
        // docs
        api_문서_타이틀("feed_collection_request_read_feed_list_success", spec);

        // given
        String collectionId = 피드_컬렉션_등록하고_피드_리스트도_추가한다(moodIds, 회원아티_액세스토큰, feedIds);
        Collections.reverse(feedIds);

        // when
        var response = 피드_컬렉션_피드리스트를_조회한다(collectionId, 회원아티_액세스토큰, feedIds, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
    }

    @DisplayName("피드 켈렉션의 피드리스트를 수정 요청 성공하면 응답 코드 204를 반환한다.")
    @Test
    void when_request_to_update_feed_list_then_response_code_204() {
        // docs
        api_문서_타이틀("feed_collection_request_update_feed_list_success", spec);

        // given
        var collectionId = 피드_컬렉션_등록하고_아이디를_가져온다(moodIds, 회원아티_액세스토큰);
        Collections.reverse(feedIds);

        // when
        var response = 피드_컬렉션_피드리스트를_수정한다(collectionId, 회원아티_액세스토큰, feedIds, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(204);
    }

    @DisplayName("피드 컬렉션의 피드를 추가 요청 성공하면 응답 코드 201를 반환한다.")
    @Test
    void when_request_to_add_feed_to_collection_then_respond_code_201() {
        // docs
        api_문서_타이틀("feed_collection_request_add_feed_success", spec);

        // given
        String collectionId = 피드_컬렉션_등록하고_아이디를_가져온다(moodIds, 회원아티_액세스토큰);
        String feedId = feedIds.get(0);

        // when
        var response = 피드_컬렉션에_피드를_추가한다(collectionId, feedId, 회원아티_액세스토큰, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @DisplayName("피드 컬렉션의 피드를 삭제 요청 성공하면 응답 코드 204를 반환한다.")
    @Test
    void when_request_to_delete_feed_to_collection_then_respond_code_204() {
        // docs
        api_문서_타이틀("feed_collection_request_delete_feed_success", spec);

        // given
        String collectionId = 피드_컬렉션_등록하고_피드_리스트도_추가한다(moodIds, 회원아티_액세스토큰, feedIds);
        String feedId = feedIds.get(0);

        // when
        var response = 피드_컬렉션에_피드를_삭제한다(collectionId, feedId, 회원아티_액세스토큰, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @DisplayName("피드 컬렉션을 삭제 요청 성공하면 응답 코드 204를 반환한다.")
    @Test
    void when_request_to_delete_feed_collection_then_respond_code_204() {
        // docs
        api_문서_타이틀("feed_collection_request_delete_success", spec);

        // given
        var collectionId = 피드_컬렉션_등록하고_아이디를_가져온다(moodIds, 회원아티_액세스토큰);

        // when
        var response = 피드_컬렉션을_삭제한다(collectionId, 회원아티_액세스토큰, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(204);
    }
}

