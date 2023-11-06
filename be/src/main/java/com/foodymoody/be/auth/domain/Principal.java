package com.foodymoody.be.auth.domain;

public class Principal {

    private String id;
    private String email;

    private Principal(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public static Principal of(String id, String email) {
        return new Principal(id, email);
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
