package com.foodymoody.be.member.application;

import com.foodymoody.be.member.domain.PasswordMatch;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String password = getStringFieldByGetter(value, "getPassword");
        String repeatPassword = getStringFieldByGetter(value, "getRepeatPassword");
        return Objects.equals(repeatPassword, password);
    }

    private static String getStringFieldByGetter(Object value, String getter) {
        try {
            Class<?> c = value.getClass();
            Method method = c.getMethod(getter);
            Object invoke = method.invoke(value);
            return Objects.nonNull(invoke) ? invoke.toString() : null;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

}
