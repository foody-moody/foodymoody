package com.foodymoody.be.common.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.foodymoody.be.common.exception.ContentIsEmptyException;
import com.foodymoody.be.common.exception.ContentIsOver200Exception;
import com.foodymoody.be.common.exception.ContentIsSpaceException;
import com.foodymoody.be.common.exception.ContentNotExistsException;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Embeddable
public class Content {

    public static final int COUNT_MAX_SIZE = 200;

    @JsonValue
    @NotNull
    @NotBlank
    @Size(max = 200)
    @JsonProperty("content")
    private String value;

    public Content(String value) {
        validateContent(value);
        this.value = value;
    }

    public static void validateContent(String content) {
        if (isNull(content)) {
            throw new ContentNotExistsException();
        }
        if (isEmpty(content)) {
            throw new ContentIsEmptyException();
        }
        if (isBlank(content)) {
            throw new ContentIsSpaceException();
        }
        if (isOver200(content)) {
            throw new ContentIsOver200Exception();
        }
    }

    public static boolean isOver200(String content) {
        return content.length() > COUNT_MAX_SIZE;
    }

    public static boolean isBlank(String content) {
        return content.isBlank();
    }

    public static boolean isEmpty(String content) {
        return content.isEmpty();
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

}
