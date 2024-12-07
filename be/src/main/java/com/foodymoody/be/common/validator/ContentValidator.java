package com.foodymoody.be.common.validator;

import com.foodymoody.be.common.exception.ContentNotExistsException;
import com.foodymoody.be.common.util.Constants;
import com.foodymoody.be.common.util.Content;

public class ContentValidator {

    private ContentValidator() {
        throw new AssertionError(Constants.UTILITY_CLASS);
    }

    public static void validate(Content content) {
        if (content == null) {
            throw new ContentNotExistsException();
        }
    }

}
