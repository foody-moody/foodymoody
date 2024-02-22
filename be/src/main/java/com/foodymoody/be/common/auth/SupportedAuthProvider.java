package com.foodymoody.be.common.auth;

public enum SupportedAuthProvider implements AuthProvider {

    LOCAL("local"),
    GOOGLE("google");

    private String name;

    SupportedAuthProvider(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
