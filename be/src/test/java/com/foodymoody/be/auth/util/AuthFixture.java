package com.foodymoody.be.auth.util;

import static com.foodymoody.be.member.util.MemberFixture.비회원_보노;
import static com.foodymoody.be.member.util.MemberFixture.비회원_설리;
import static com.foodymoody.be.member.util.MemberFixture.비회원_알버트;
import static com.foodymoody.be.member.util.MemberFixture.회원_푸반;

import com.foodymoody.be.auth.controller.dto.LoginRequest;

public class AuthFixture {

    public static LoginRequest 알버트_로그인_요청() {
        return new LoginRequest(비회원_알버트.getEmail(), 비회원_알버트.getPassword());
    }

    public static LoginRequest 푸반_로그인_요청() {
        return new LoginRequest(회원_푸반.getEmail(), 회원_푸반.getPassword());
    }

    public static LoginRequest 보노_로그인_요청() {
        return new LoginRequest(비회원_보노.getEmail(), 비회원_보노.getPassword());
    }

    public static LoginRequest 설리_로그인_요청() {
        return new LoginRequest(비회원_설리.getEmail(), 비회원_설리.getPassword());
    }
}
