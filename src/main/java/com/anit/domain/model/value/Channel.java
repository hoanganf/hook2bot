package com.anit.domain.model.value;

/**
 * @author anit
 */
public class Channel extends StringValue {
    private Channel(String value) {
        super(value);
    }

    public static Channel of(String value) {
        return new Channel(value);
    }

}

