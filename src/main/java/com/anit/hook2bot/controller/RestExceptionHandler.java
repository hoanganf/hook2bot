package com.anit.hook2bot.controller;

import com.anit.domain.model.ApiException;
import com.anit.domain.model.value.ErrorCode;
import com.anit.domain.model.value.ErrorMessage;
import com.anit.hook2bot.controller.resource.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        if (ex instanceof ApiException) {
            ApiException apiException = (ApiException) ex;
            log.error("Error. errorCode={}, errorMessage={}, exception={}", apiException.getErrorCode(), apiException.getErrorMessage(), ErrorMessage.convertToStringFrom(apiException.getCause()));
            return handleExceptionInternal(ex, ErrorResponse.of(apiException.getErrorCode().toString(), apiException.getErrorMessage().toString()),
                    new HttpHeaders(), convertHttpStatus(apiException.getErrorCode()), request);
        }
        log.error("Error. exception= {}", ErrorMessage.convertToStringFrom(ex));
        return handleExceptionInternal(ex, ErrorResponse.of(ErrorCode.SYSTEM_ERROR.toString(), "SystemError"),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);

    }

    public HttpStatus convertHttpStatus(ErrorCode errorCode) {
        switch (errorCode) {
            case NOT_FOUND:
                return HttpStatus.NOT_FOUND;
            case BAD_REQUEST:
                return HttpStatus.BAD_REQUEST;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;

        }
    }

}