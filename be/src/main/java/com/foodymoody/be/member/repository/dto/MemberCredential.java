package com.foodymoody.be.member.repository.dto;

import lombok.Getter;

@Getter
public class MemberCredential {

    private String id;
    private String email;
    private String password;

    public MemberCredential(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static MemberCredential of(String id, String email, String password) {
        return new MemberCredential(id, email, password);
    }
}
