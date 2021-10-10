package com.anit.domain.model.value.google;

import com.anit.domain.model.value.StringValue;

/**
 * @author anit
 */
public class Key extends StringValue {
    private Key(String value) {
        super(value);
    }

    public static Key of(String value) {
        return new Key(value);
    }

}

