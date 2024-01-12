package com.foodymoody.be.common.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.foodymoody.be.common.exception.InvalidFeedIdException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedIdValidatorTest {

    @DisplayName("피드 아이디가 null이면 예외가 발생한다")
    @Test
    void validate_when_feed_id_is_null_then_throw_exception() {
        assertThatThrownBy(() -> FeedIdValidator.validate(null))
                .isInstanceOf(InvalidFeedIdException.class);
    }
}
