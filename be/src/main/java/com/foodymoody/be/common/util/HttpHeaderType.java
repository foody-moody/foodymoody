package com.foodymoody.be.common.util;

public enum HttpHeaderType {

    AUTHORIZATION("Authorization", "Bearer");

    public final String NAME;
    public final String SKIM;

    HttpHeaderType(String headerName, String skim) {
        this.NAME = headerName;
        this.SKIM = skim;
    }

}
