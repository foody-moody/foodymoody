package com.foodymoody.be.common.util;

public class Constants {

    public static final String UTILITY_CLASS = "This is a utility class and cannot be instantiated";

    private Constants() {
        throw new IllegalStateException(UTILITY_CLASS);
    }
}
