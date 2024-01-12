package com.foodymoody.be.common.validator;

import com.foodymoody.be.common.exception.InvalidReplyIdException;
import com.foodymoody.be.common.util.Constants;
import com.foodymoody.be.common.util.ids.ReplyId;

public class ReplyIdValidator {

    private ReplyIdValidator() {
        throw new AssertionError(Constants.UTILITY_CLASS);
    }

    public static void validate(ReplyId id) {
        if (id == null) {
            throw new InvalidReplyIdException();
        }
    }
}
