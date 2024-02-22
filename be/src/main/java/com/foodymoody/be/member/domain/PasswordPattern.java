package com.foodymoody.be.member.domain;

import com.foodymoody.be.member.application.PasswordPatternValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordPatternValidator.class)
public @interface PasswordPattern {

    String message() default "비밀번호는 8글자 이상 16글자 이하의 영문자, 특수문자로 이뤄져야 합니다";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
