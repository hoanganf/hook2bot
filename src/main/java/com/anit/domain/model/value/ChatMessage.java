package com.anit.domain.model.value;

/**
 * @author anit
 */
public class ChatMessage extends StringValue {
    private ChatMessage(String value) {
        super(value);
    }

    public static ChatMessage of(String value) {
        return new ChatMessage(value);
    }

}
