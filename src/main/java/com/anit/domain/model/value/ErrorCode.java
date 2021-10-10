package com.anit.domain.model.value;

import com.anit.domain.model.ApiException;

/**
 * @author anit
 */
public enum ErrorCode {
    NOT_FOUND("10001"),
    BAD_REQUEST("10002"),
    SYSTEM_ERROR("90000");

    private final String value;

    ErrorCode(String value) {
        this.value = value;
    }

    public static ErrorCode of(String value) {
        for (ErrorCode e : values()) {
            if (e.value.equalsIgnoreCase(value)) {
                return e;
            }
        }
        throw new ApiException(ErrorCode.SYSTEM_ERROR, ErrorMessage.of("ErrorCode value not valid. value=".concat(value)));
    }

    @Override
    public String toString() {
        return value;
    }
}
