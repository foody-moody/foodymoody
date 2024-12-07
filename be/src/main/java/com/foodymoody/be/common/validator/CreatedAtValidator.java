package com.foodymoody.be.common.validator;

import com.foodymoody.be.common.exception.CreateTimeIsNullException;
import com.foodymoody.be.common.util.Constants;
import java.time.LocalDateTime;

public class CreatedAtValidator {

    private CreatedAtValidator() {
        throw new AssertionError(Constants.UTILITY_CLASS);
    }

    public static void validate(LocalDateTime createdAt) {
        if (createdAt == null) {
            throw new CreateTimeIsNullException();
        }
    }

}
