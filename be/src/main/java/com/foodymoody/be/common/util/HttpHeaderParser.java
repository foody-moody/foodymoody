package com.foodymoody.be.common.util;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.common.exception.RequestHeaderNotFoundException;
import java.util.Objects;

public class HttpHeaderParser {

    private HttpHeaderParser() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static String parse(String header, HttpHeaderType type) {
        return parse(header, type.SKIM);
    }

    private static String parse(String header, String skim) {
        if (Objects.isNull(header)) {
            throw new RequestHeaderNotFoundException();
        }
        return header.substring(skim.length() + 1);
    }
}
