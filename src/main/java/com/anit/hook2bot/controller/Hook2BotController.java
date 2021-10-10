package com.anit.hook2bot.controller;

import com.anit.domain.model.CheckWebhookResult;
import com.anit.domain.model.value.GitEvent;
import com.anit.domain.model.value.Payload;
import com.anit.domain.model.value.google.Key;
import com.anit.domain.model.value.google.Space;
import com.anit.domain.model.value.google.Token;
import com.anit.domain.service.WebhookService;
import com.anit.hook2bot.infrastructure.client.GoogleChatBotClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author anit
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class Hook2BotController {
    private static final String template = "Hello, %s!";
    private final GoogleChatBotClient client;
    private final WebhookService webhookService;

    @PostMapping("/v1/hook2bot/google/spaces/{space}/messages")
    public ResponseEntity<String> hookToGoogle(
            @RequestHeader("X-Github-Event") String gitEventStr,
            @PathVariable Space space,
            @RequestParam(value = "key") Key key,
            @RequestParam(value = "token") Token token,
            @RequestBody Map<String, ?> payloadMap) throws JsonProcessingException {
        log.info("key: {},token: {}, gitEvent: {}", key, token, gitEventStr);

        log.info("event: {},payload: {}", gitEventStr, new ObjectMapper().writeValueAsString(payloadMap));
        GitEvent gitEvent = GitEvent.of(gitEventStr);
        Payload payload = Payload.of(payloadMap);
        CheckWebhookResult result = webhookService.checkHook(gitEvent, payload);
        if (result.shouldSendMessageToChat()) {
            client.pushMessage(space, result.getChatMessage(), key, token);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .contentType(MediaType.TEXT_HTML)
                .body("Thank you!");
    }
}