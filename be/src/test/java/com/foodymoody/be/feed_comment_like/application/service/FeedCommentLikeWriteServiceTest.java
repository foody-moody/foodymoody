package com.foodymoody.be.feed_comment_like.application.service;

import static org.mockito.BDDMockito.given;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_comment_like.application.exception.FeedCommentIsAlreadyLikedException;
import com.foodymoody.be.feed_comment_like.application.exception.FeedCommentIsNotLikedException;
import com.foodymoody.be.feed_comment_like.domain.FeedCommentLikeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("댓글 하트 쓰기 서비스 테스트")
class FeedCommentLikeWriteServiceTest {

    @InjectMocks
    private FeedCommentLikeWriteService feedCommentLikeWriteService;

    @Mock
    private FeedCommentLikeRepository feedCommentLikeRepository;


    @DisplayName("댓글 하트를 등록된 상태에서 다시 등록하면 예외가 발생한다.")
    @Test
    void register() {
        // given
        var feedCommentId = IdFactory.createFeedCommentId();
        var memberId = IdFactory.createMemberId();
        given(feedCommentLikeRepository.existsByFeedCommentIdAndMemberId(feedCommentId, memberId))
                .willReturn(true);

        // when
        Assertions.assertThatThrownBy(() -> feedCommentLikeWriteService.register(feedCommentId, memberId))
                .isInstanceOf(FeedCommentIsAlreadyLikedException.class);
    }

    @DisplayName("댓글 하트를 등록되지 않은 상태에서 삭제하면 예외가 발생한다.")
    @Test
    void delete() {
        // given
        var feedCommentId = IdFactory.createFeedCommentId();
        var memberId = IdFactory.createMemberId();
        given(feedCommentLikeRepository.existsByFeedCommentIdAndMemberId(feedCommentId, memberId))
                .willReturn(false);

        // when
        Assertions.assertThatThrownBy(() -> feedCommentLikeWriteService.delete(feedCommentId, memberId))
                .isInstanceOf(FeedCommentIsNotLikedException.class);
    }
}
