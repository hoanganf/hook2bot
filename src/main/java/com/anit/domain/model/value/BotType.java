package com.anit.domain.model.value;

import com.anit.domain.model.ApiException;

/**
 * @author anit
 */
public enum BotType {
    GOOGLE("googleChat"),
    SLACK("slack");

    private final String value;

    BotType(String value) {
        this.value = value;
    }

    public static BotType of(String value) {
        for (BotType e : values()) {
            if (e.value.equalsIgnoreCase(value)) {
                return e;
            }
        }
        throw new ApiException(ErrorCode.BAD_REQUEST, ErrorMessage.of("BotType value not valid. value=".concat(value)));
    }

    @Override
    public String toString() {
        return value;
    }
}
