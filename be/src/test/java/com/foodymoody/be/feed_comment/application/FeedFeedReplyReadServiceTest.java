package com.foodymoody.be.feed_comment.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.foodymoody.be.common.exception.ErrorMessage;
import com.foodymoody.be.common.exception.ReplyNotExistsException;
import com.foodymoody.be.feed_comment.application.service.FeedReplyReadService;
import com.foodymoody.be.feed_comment.domain.repository.FeedReplyRepository;
import com.foodymoody.be.feed_comment.util.FeedCommentFixture;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("댓글 읽기 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class FeedFeedReplyReadServiceTest {

    @InjectMocks
    FeedReplyReadService feedReplyReadService;

    @Mock
    FeedReplyRepository feedReplyRepository;


    @DisplayName("대댓글 조회 시 대댓글이 없으면 에외를 던진다")
    @Test
    void fetch_by_id_if_not_exists() {
        // given
        var id = FeedCommentFixture.notExistsReplyId();

        // when,then
        assertThatThrownBy(() -> feedReplyReadService.fetchById(id))
                .isInstanceOf(ReplyNotExistsException.class)
                .message().isEqualTo(ErrorMessage.REPLY_NOT_EXISTS.getMessage());
    }

    @DisplayName("대댓글 조회 시 대댓글이 있으면 에외를 던지지 않는다")
    @Test
    void fetch_by_id_validate_if_exists() {
        // given
        var givenResult = Optional.of(FeedCommentFixture.reply());
        given(feedReplyRepository.findById(any()))
                .willReturn(givenResult);

        // when,then
        feedReplyReadService.fetchById(FeedCommentFixture.feedReplyId());
    }

    @DisplayName("대댓글 아이디를 검증 시 대댓글이 없으면 에외를 던진다")
    @Test
    void validate_if_not_exists() {
        // given
        var id = FeedCommentFixture.notExistsReplyId();

        // when,then
        assertThatThrownBy(() -> feedReplyReadService.validate(id))
                .isInstanceOf(ReplyNotExistsException.class)
                .message().isEqualTo(ErrorMessage.REPLY_NOT_EXISTS.getMessage());
    }

    @DisplayName("대댓글 아이디를 검증 시 대댓글이 있으면 에외를 던지지 않는다")
    @Test
    void validate_if_exists() {
        // given
        given(feedReplyRepository.existsById(any()))
                .willReturn(true);

        // when,then
        feedReplyReadService.validate(FeedCommentFixture.feedReplyId());
    }
}
