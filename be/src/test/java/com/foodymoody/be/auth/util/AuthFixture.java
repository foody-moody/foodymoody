package com.foodymoody.be.auth.util;

import static com.foodymoody.be.member.util.MemberFixture.사용자_보노;
import static com.foodymoody.be.member.util.MemberFixture.사용자_설리;
import static com.foodymoody.be.member.util.MemberFixture.사용자_아티;
import static com.foodymoody.be.member.util.MemberFixture.사용자_알버트;
import static com.foodymoody.be.member.util.MemberFixture.사용자_푸반;

import java.util.Map;

public class AuthFixture {

    public static Map<String, Object> 알버트_로그인_요청() {
        return Map.of(
                "email", 사용자_알버트.getEmail(),
                "password", 사용자_알버트.getPassword());
    }

    public static Map<String, Object> 푸반_로그인_요청() {
        return Map.of(
                "email", 사용자_푸반.getEmail(),
                "password", 사용자_푸반.getPassword());
    }

    public static Map<String, Object> 보노_로그인_요청() {
        return Map.of(
                "email", 사용자_보노.getEmail(),
                "password", 사용자_보노.getPassword());
    }

    public static Map<String, Object> 설리_로그인_요청() {
        return Map.of(
                "email", 사용자_설리.getEmail(),
                "password", 사용자_설리.getPassword());
    }

    public static Map<String, Object> 아티_로그인_요청() {
        return Map.of(
                "email", 사용자_아티.getEmail(),
                "password", 사용자_아티.getPassword());
    }

    public static Map<String, Object> 푸반_로그인_요청_틀린_비밀번호() {
        return Map.of(
                "email", 사용자_푸반.getEmail(),
                "password", "wrongPassword");
    }

    public static Map<String, Object> 푸반_로그인_요청_수정된_비밀번호(String 수정된_비밀번호) {
        return Map.of(
                "email", 사용자_푸반.getEmail(),
                "password", 수정된_비밀번호);
    }

}
