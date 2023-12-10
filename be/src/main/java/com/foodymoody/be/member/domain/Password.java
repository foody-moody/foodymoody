package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.exception.IncorrectMemberPasswordException;
import com.foodymoody.be.common.exception.PasswordPatternNotMatchException;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    private static final String REGEX = "^.{8,}$";

    private String value;

    public Password(String value) {
        validate(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void validateEquals(String password) {
        if (Objects.isNull(password) || !Objects.equals(password, this.value)) {
            throw new IncorrectMemberPasswordException();
        }
    }

    private void validate(String value) {
        if (!Pattern.matches(REGEX, value)) {
            throw new PasswordPatternNotMatchException();
        }
    }
}
