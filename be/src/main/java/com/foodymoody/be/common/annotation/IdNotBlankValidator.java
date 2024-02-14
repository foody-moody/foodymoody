package com.foodymoody.be.common.annotation;

import com.foodymoody.be.common.util.ids.BaseId;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdNotBlankValidator implements ConstraintValidator<IdNotBlank, BaseId> {

    @Override
    public boolean isValid(BaseId id, ConstraintValidatorContext context) {
        String value = id.getValue();
        return Objects.nonNull(value) && !value.isBlank();
    }
}
