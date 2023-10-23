package com.foodymoody.be.comment.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @DisplayName("댓글 등록 시 댓글 내용이 없으면 예외가 발생한다.")
    @Test
    void when_register_comment_if_content_not_exists_throw_exception() {
        // given
        RegisterCommentRequest request = new RegisterCommentRequest();
        request.setFeedId(1L);

        // when,then
        assertThatThrownBy(() -> commentService.registerComment(request))
                .isInstanceOf(ContentNotExistsException.class);
    }
}
