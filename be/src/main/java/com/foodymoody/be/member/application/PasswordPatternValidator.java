package com.foodymoody.be.member.application;

import com.foodymoody.be.member.domain.PasswordPattern;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordPatternValidator implements ConstraintValidator<PasswordPattern, String> {

    private static final String REGEX = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{8,16}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Pattern.matches(REGEX, value);
    }
}
