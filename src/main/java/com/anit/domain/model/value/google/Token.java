package com.anit.domain.model.value.google;

import com.anit.domain.model.value.StringValue;

/**
 * @author anit
 */
public class Token extends StringValue {
    private Token(String value) {
        super(value);
    }

    public static Token of(String value) {
        return new Token(value);
    }

}

