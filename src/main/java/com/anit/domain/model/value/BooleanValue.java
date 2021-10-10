package com.anit.domain.model.value;

/**
 * @author anit
 */
public class BooleanValue extends AbstractValue<Boolean> {
    protected BooleanValue(Boolean value) {
        super(value);
    }

    protected BooleanValue(String value) {
        super(Boolean.valueOf(value));
    }

    public static BooleanValue ofTrue() {
        return new BooleanValue(Boolean.TRUE);
    }

    public static BooleanValue ofFalse() {
        return new BooleanValue(Boolean.FALSE);
    }

    public boolean toBoolean() {
        return this.getValue();
    }
}
