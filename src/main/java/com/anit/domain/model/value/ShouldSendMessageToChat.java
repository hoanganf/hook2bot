package com.anit.domain.model.value;

/**
 * @author anit
 */
public class ShouldSendMessageToChat extends BooleanValue {
    private ShouldSendMessageToChat(String value) {
        super(value);
    }

    private ShouldSendMessageToChat(Boolean value) {
        super(value);
    }

    public static ShouldSendMessageToChat of(Boolean value) {
        return new ShouldSendMessageToChat(value);
    }

    public static ShouldSendMessageToChat ofTrue() {
        return new ShouldSendMessageToChat(Boolean.TRUE);
    }

    public static ShouldSendMessageToChat ofFalse() {
        return new ShouldSendMessageToChat(Boolean.FALSE);
    }

}

