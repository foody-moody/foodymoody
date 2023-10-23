package com.foodymoody.be.common.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(ContentNotExistsException.class)
    public String handleContentNotExistsException(ContentNotExistsException e) {
        return e.getMessage();
    }

    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(ContentIsEmptyException.class)
    public String handleContentIsEmptyException(ContentIsEmptyException e) {
        return e.getMessage();
    }

    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(ContentIsSpaceException.class)
    public String handleContentIsSpaceException(ContentIsSpaceException e) {
        return e.getMessage();
    }
}
