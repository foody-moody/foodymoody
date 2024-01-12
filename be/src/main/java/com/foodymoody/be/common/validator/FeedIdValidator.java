package com.foodymoody.be.common.validator;

import com.foodymoody.be.common.exception.InvalidFeedIdException;
import com.foodymoody.be.common.util.Constants;
import com.foodymoody.be.common.util.ids.FeedId;

public class FeedIdValidator {

    private FeedIdValidator() {
        throw new AssertionError(Constants.UTILITY_CLASS);
    }

    public static void validate(FeedId id) {
        if (id == null) {
            throw new InvalidFeedIdException();
        }
    }
}
