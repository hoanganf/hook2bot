package com.anit.domain.model.value;

import lombok.Getter;

/**
 * @author anit
 */
@Getter
public abstract class AbstractValue<T> {
    private final T value;

    protected AbstractValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
