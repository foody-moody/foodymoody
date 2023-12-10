package com.foodymoody.be.common.util;

public class Constants {

    public static final String UTILITY_CLASS = "This is a utility class and cannot be instantiated";
    public static final String MEMBER_PROFILE_DEFAULT_IMAGE_ID = "member-profile-default";

    private Constants() {
        throw new IllegalStateException(UTILITY_CLASS);
    }
}
