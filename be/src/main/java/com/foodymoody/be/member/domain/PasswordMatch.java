package com.foodymoody.be.member.domain;

import com.foodymoody.be.member.application.PasswordMatchValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
public @interface PasswordMatch {

    String message() default "재입력한 비밀번호가 일치하지 않습니다";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
