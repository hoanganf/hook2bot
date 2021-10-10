package com.anit.hook2bot.controller.resource;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author anit
 */
@RequiredArgsConstructor(staticName = "of")
@Getter
public class WebhookResponse {
    private final Boolean success;
}
