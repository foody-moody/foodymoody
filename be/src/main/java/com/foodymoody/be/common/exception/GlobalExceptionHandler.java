package com.foodymoody.be.common.exception;

import static com.foodymoody.be.common.exception.ErrorMessage.INVALID_INPUT_VALUE;
import static com.foodymoody.be.common.exception.ErrorMessage.MAX_UPLOAD_SIZE_EXEEDED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public ErrorResponse handleBusinessException(BusinessException e) {
        log.error("handleBusinessException", e);
        return new ErrorResponse(e.getMessage(), e.getCode());
    }

    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        Map<String, String> errors = getErrors(e);
        return new ErrorResponse(INVALID_INPUT_VALUE.getMessage(), INVALID_INPUT_VALUE.getCode(), errors);
    }

    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("handleHttpMessageNotReadableException", e);
        return new ErrorResponse(INVALID_INPUT_VALUE.getMessage(), INVALID_INPUT_VALUE.getCode());
    }

    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("handleIllegalArgumentException", e);
        return new ErrorResponse(e.getMessage(), INVALID_INPUT_VALUE.getCode());
    }

    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(MissingRequestValueException.class)
    public ErrorResponse handleIllegalArgumentException(MissingRequestValueException e) {
        log.error("handleMissingRequestValueExceptionException", e);
        return new ErrorResponse(INVALID_INPUT_VALUE.getMessage(), INVALID_INPUT_VALUE.getCode());
    }

    @ResponseStatus(value = UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponse handleUnauthorizedException(UnauthorizedException e) {
        log.error("handleUnauthorizedExceptionException", e);
        return new ErrorResponse(e.getMessage(), e.getCode());
    }

    @ResponseStatus(value = NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error("handleResourceNotFoundExceptionException", e);
        return new ErrorResponse(e.getMessage(), e.getCode());
    }

    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ErrorResponse handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("handleMaxUploadSizeExceededException", e);
        return new ErrorResponse(MAX_UPLOAD_SIZE_EXEEDED.getMessage(), MAX_UPLOAD_SIZE_EXEEDED.getCode());
    }

    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(MultipartException.class)
    public ErrorResponse handleMultipartException(MultipartException e) {
        log.error("handleMultipartException", e);
        return new ErrorResponse(e.getMessage(), INVALID_INPUT_VALUE.getCode());
    }

    private static Map<String, String> getErrors(MethodArgumentNotValidException e) {
        return e.getBindingResult()
                .getAllErrors()
                .stream()
                .filter(FieldError.class::isInstance)
                .collect(Collectors.toMap(
                        error -> ((FieldError) error).getField(),
                        ObjectError::getDefaultMessage,
                        (msg1, msg2) -> msg1 + ";" + msg2
                ));
    }
}
