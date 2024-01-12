package com.foodymoody.be.common.validator;

import com.foodymoody.be.common.exception.InvalidMemberIdException;
import com.foodymoody.be.common.util.Constants;
import com.foodymoody.be.common.util.ids.MemberId;

public class MemberIdValidator {

    private MemberIdValidator() {
        throw new AssertionError(Constants.UTILITY_CLASS);
    }

    public static void validate(MemberId id) {
        if (id == null) {
            throw new InvalidMemberIdException();
        }
    }
}
