package com.foodymoody.be.feed_reply_like.application.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_reply_like.application.service.FeedReplyLikeWriteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FeedReplyLikeWriteUseCaseTest {

    @InjectMocks
    private FeedReplyLikeWriteUseCase useCase;

    @Mock
    private FeedReplyLikeWriteService feedReplyLikeService;

    @DisplayName("이미 대댓글에 좋아요를 누른 경우, 좋아요를 누르지 않는다.")
    @Test
    void register() {
        // given
        var feedCommentId = IdFactory.createFeedCommentId();
        var feedReplyId = IdFactory.createFeedReplyId();
        var memberId = IdFactory.createMemberId();
        given(feedReplyLikeService.existsByReplyIdAndMemberId(any(), any())).willReturn(true);

        // when
        useCase.register(feedCommentId, feedReplyId, memberId);

        // then
        verify(feedReplyLikeService, never()).register(any(), any(), any());
    }

    @DisplayName("좋아요 누루지 않은 상태에서 좋아요를 취소할 때 좋아요 취소를 하지 않는다.")
    @Test
    void delete() {
        // given
        var feedReplyId = IdFactory.createFeedReplyId();
        var memberId = IdFactory.createMemberId();
        given(feedReplyLikeService.existsByReplyIdAndMemberId(any(), any())).willReturn(false);

        // when
        useCase.delete(feedReplyId, memberId);

        // then
        verify(feedReplyLikeService, never()).delete(any(), any());
    }

}
