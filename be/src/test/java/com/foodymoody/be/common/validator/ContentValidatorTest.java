package com.foodymoody.be.common.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.foodymoody.be.common.exception.ContentNotExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContentValidatorTest {

    @DisplayName("컨텐츠가 null이면 예외가 발생한다")
    @Test
    void validate_when_content_is_null_then_throw_exception() {
        assertThatThrownBy(() -> ContentValidator.validate(null))
                .isInstanceOf(ContentNotExistsException.class);
    }
}
