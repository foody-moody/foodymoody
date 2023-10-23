package com.foodymoody.be.comment.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import com.foodymoody.be.common.exception.ContentIsEmptyException;
import com.foodymoody.be.common.exception.ContentNotExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @DisplayName("댓글 등록 시 댓글 내용이 없으면 댓글 내용 없음 예외가 발생한다.")
    @Test
    void when_register_comment_if_content_not_exists_throw_exception() {
        // given
        RegisterCommentRequest request = new RegisterCommentRequest();
        request.setFeedId(1L);

        // when,then
        assertThatThrownBy(() -> commentService.registerComment(request))
                .isInstanceOf(ContentNotExistsException.class);
    }

    @DisplayName("댓글 등록 시 댓글 내용이 공백이면 댓글 내용 공백 예외가 발생한다.")
    @Test
    void when_register_comment_if_content_is_blank_then_throw_exception() {
        // given
        RegisterCommentRequest request = new RegisterCommentRequest();
        request.setFeedId(1L);
        request.setContent("   ");

        // when,then
        assertThatThrownBy(() -> commentService.registerComment(request))
                .isInstanceOf(ContentIsEmptyException.class);
    }
}
