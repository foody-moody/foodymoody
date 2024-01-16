package com.foodymoody.be.comment.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.foodymoody.be.comment.domain.repository.ReplyRepository;
import com.foodymoody.be.comment.util.CommentFixture;
import com.foodymoody.be.common.exception.ErrorMessage;
import com.foodymoody.be.common.exception.ReplyNotExistsException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("댓글 읽기 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class ReplyReadServiceTest {

    @InjectMocks
    ReplyReadService replyReadService;

    @Mock
    ReplyRepository replyRepository;
    @Spy
    private CommentMapper commentMapper = new CommentMapper();

    @DisplayName("대댓글 조회 시 대댓글이 없으면 에외를 던진다")
    @Test
    void fetch_by_id_if_not_exists() {
        // given
        var id = CommentFixture.notExistsReplyId();

        // when,then
        assertThatThrownBy(() -> replyReadService.fetchById(id))
                .isInstanceOf(ReplyNotExistsException.class)
                .message().isEqualTo(ErrorMessage.REPLY_NOT_EXISTS.getMessage());
    }

    @DisplayName("대댓글 조회 시 대댓글이 있으면 에외를 던지지 않는다")
    @Test
    void fetch_by_id_validate_if_exists() {
        // given
        var givenResult = Optional.of(CommentFixture.reply());
        given(replyRepository.findById(any()))
                .willReturn(givenResult);

        // when,then
        replyReadService.fetchById(CommentFixture.replyId());
    }

    @DisplayName("대댓글 아이디를 검증 시 대댓글이 없으면 에외를 던진다")
    @Test
    void validate_if_not_exists() {
        // given
        var id = CommentFixture.notExistsReplyId();

        // when,then
        assertThatThrownBy(() -> replyReadService.validate(id))
                .isInstanceOf(ReplyNotExistsException.class)
                .message().isEqualTo(ErrorMessage.REPLY_NOT_EXISTS.getMessage());
    }

    @DisplayName("대댓글 아이디를 검증 시 대댓글이 있으면 에외를 던지지 않는다")
    @Test
    void validate_if_exists() {
        // given
        given(replyRepository.existsById(any()))
                .willReturn(true);

        // when,then
        replyReadService.validate(CommentFixture.replyId());
    }
}
