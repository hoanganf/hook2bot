package com.anit.domain.model;

import com.anit.domain.model.value.ChatMessage;
import com.anit.domain.model.value.ShouldSendMessageToChat;
import lombok.Builder;
import lombok.Getter;

/**
 * @author anit
 */
@Builder
public class CheckWebhookResult {
    private final ShouldSendMessageToChat shouldSendMessageToChat;
    @Getter
    private final ChatMessage chatMessage;

    public boolean shouldSendMessageToChat() {
        return shouldSendMessageToChat.toBoolean();
    }
}
