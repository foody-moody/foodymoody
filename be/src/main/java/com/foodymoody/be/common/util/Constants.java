package com.foodymoody.be.common.util;

public class Constants {

    public static final String UTILITY_CLASS = "This is a utility class and cannot be instantiated";
    public static final String DEFAULT_MEMBER_PROFILE_IMAGE_ID = "1";

    private Constants() {
        throw new IllegalStateException(UTILITY_CLASS);
    }
}
