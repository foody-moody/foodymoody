package com.foodymoody.be.common.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.foodymoody.be.common.exception.CreateTimeIsNullException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CreatedAtValidatorTest {

    @DisplayName("생성 시간이 null이면 예외가 발생한다")
    @Test
    void validate_when_created_at_is_null_then_throw_exception() {
        assertThatThrownBy(() -> CreatedAtValidator.validate(null))
                .isInstanceOf(CreateTimeIsNullException.class);
    }

}
