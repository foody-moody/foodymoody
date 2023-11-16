package com.foodymoody.be.common.util;

public enum HttpHeaderType {

    AUTHORIZATION("Authorization", "Bearer");

    public final String headerName;
    public final String skim;

    HttpHeaderType(String headerName, String skim) {
        this.headerName = headerName;
        this.skim = skim;
    }

}
