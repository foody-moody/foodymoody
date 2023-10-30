package com.foodymoody.be.comment.util;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.foodymoody.be.comment.domain.CommentValidator;
import com.foodymoody.be.common.exception.ContentIsEmptyException;
import com.foodymoody.be.common.exception.ContentIsOver200Exception;
import com.foodymoody.be.common.exception.ContentIsSpaceException;
import com.foodymoody.be.common.exception.ContentNotExistsException;
import com.foodymoody.be.common.exception.CreateTimeIsNullException;
import com.foodymoody.be.common.exception.InvalidIdException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("댓글 유효성 검사 테스트")
class CommentValidatorTest {

    @Test
    @DisplayName("댓글 내용이 없으면 댓글 내용 없음 예외가 발생한다.")
    void when_content_is_null_then_throw_exception() {
        // when,then
        assertThatThrownBy(() -> CommentValidator.validateContent(null))
                .isInstanceOf(ContentNotExistsException.class);
    }

    @Test
    @DisplayName("댓글 내용이 공백이면 댓글 내용 공백 예외가 발생한다.")
    void when_content_is_empty_then_throw_exception() {
        // when,then
        assertThatThrownBy(() -> CommentValidator.validateContent(""))
                .isInstanceOf(ContentIsEmptyException.class);
    }

    @Test
    @DisplayName("댓글 내용이 space 뿐이면 댓글 내용 공백 예외가 발생한다.")
    void when_content_is_space_then_throw_exception() {
        // when,then
        assertThatThrownBy(() -> CommentValidator.validateContent(" "))
                .isInstanceOf(ContentIsSpaceException.class);
    }

    @Test
    @DisplayName("댓글 내용이 200자를 초과하면 댓글 내용 200자 초과 예외가 발생한다.")
    void when_content_is_over_200_then_throw_exception() {
        String content = "a".repeat(201);
        // when,then
        assertThatThrownBy(() -> CommentValidator.validateContent(content))
                .isInstanceOf(ContentIsOver200Exception.class);
    }

    @Test
    @DisplayName("댓글 내용이 200자를 초과하지 않으면 댓글 내용 200자 초과 예외가 발생하지 않는다.")
    void when_content_is_not_over_200_then_not_throw_exception() {
        // when,then
        assertThatCode(() -> CommentValidator.validateContent("a".repeat(200)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("아이디가 null이면 아이디 예외가 발생한다.")
    void when_id_is_null_then_throw_exception() {
        // when,then
        assertThatThrownBy(() -> CommentValidator.validateId(null))
                .isInstanceOf(InvalidIdException.class);
    }

    @Test
    @DisplayName("생성시간이 null이면 예외가 발생한다.")
    void when_created_at_is_null_then_throw_exception() {
        // when,then
        assertThatThrownBy(() -> CommentValidator.validateCreateTime(null))
                .isInstanceOf(CreateTimeIsNullException.class);
    }
}
