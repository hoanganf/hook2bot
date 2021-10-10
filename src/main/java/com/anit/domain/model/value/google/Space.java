package com.anit.domain.model.value.google;

import com.anit.domain.model.value.StringValue;

/**
 * @author anit
 */
public class Space extends StringValue {
    private Space(String value) {
        super(value);
    }

    public static Space of(String value) {
        return new Space(value);
    }

}

