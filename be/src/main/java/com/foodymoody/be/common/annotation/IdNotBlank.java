package com.foodymoody.be.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdNotBlankValidator.class)
public @interface IdNotBlank {

    String message() default "id가 공백일 수 없습니다";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
