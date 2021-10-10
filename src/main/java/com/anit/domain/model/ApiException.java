package com.anit.domain.model;

import com.anit.domain.model.value.ErrorCode;
import com.anit.domain.model.value.ErrorMessage;
import lombok.Getter;
import lombok.ToString;

@Getter
public class ApiException extends RuntimeException {
    private final ErrorCode errorCode;
    private final ErrorMessage errorMessage;

    public ApiException(ErrorCode errorCode, ErrorMessage errorMessage, Throwable throwable) {
        super(throwable);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ApiException(ErrorCode errorCode, ErrorMessage errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
