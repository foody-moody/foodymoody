package com.foodymoody.be.feed_comment.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import com.foodymoody.be.common.exception.CommentNotExistsException;
import com.foodymoody.be.common.exception.ErrorMessage;
import com.foodymoody.be.feed_comment.domain.repository.CommentRepository;
import com.foodymoody.be.feed_comment.util.CommentFixture;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("댓글 조회 서비스")
@ExtendWith(MockitoExtension.class)
class FeedCommentReadServiceTest {

    @InjectMocks
    private CommentReadService commentReadService;
    @Mock
    private CommentRepository commentRepository;

    @DisplayName("댓글 조회 시 댓글이 없으면 에외를 던진다")
    @Test
    void when_fetch_comment_if_comment_not_exists_then_throw_exception() {
        // given
        var id = CommentFixture.notExistsCommentId();
        given(commentRepository.findById(id)).willReturn(Optional.empty());

        // when,then
        assertThatThrownBy(() -> commentReadService.fetchById(id))
                .isInstanceOf(CommentNotExistsException.class)
                .message().isEqualTo(ErrorMessage.COMMENT_NOT_EXISTS.getMessage());
    }
}