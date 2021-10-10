package com.anit.hook2bot.infrastructure.client;

import com.anit.domain.model.ApiException;
import com.anit.domain.model.value.ChatMessage;
import com.anit.domain.model.value.ErrorCode;
import com.anit.domain.model.value.ErrorMessage;
import com.anit.domain.model.value.google.Key;
import com.anit.domain.model.value.google.Space;
import com.anit.domain.model.value.google.Token;
import com.anit.hook2bot.infrastructure.client.resource.GoogleChatRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author anit
 */
@Slf4j
@Component
public class GoogleChatBotClient {
    private final String host;
    private final RestTemplate restTemplate;

    public GoogleChatBotClient(
            @Value("${hook2bot.google-api.host:https://chat.googleapis.com}") String host,
            @Qualifier("googleChatRestTemplate") RestTemplate restTemplate) {
        this.host = host;
        this.restTemplate = restTemplate;
    }

    public boolean pushMessage(Space space, ChatMessage chatMessage, Key key, Token token) {
        try {
            UriComponents uriComponents = UriComponentsBuilder
                    .fromUriString(host.concat("/v1/spaces/{space}/messages?key={key}&token={token}"))
                    .buildAndExpand(space.toString(), key.toString(), token.toString());
            GoogleChatRequest request = new GoogleChatRequest(chatMessage.toString());
            restTemplate.postForEntity(uriComponents.toUri(), request, Void.class);
            return true;
        } catch (HttpClientErrorException ex) {
            if (HttpStatus.BAD_REQUEST == ex.getStatusCode()) {
                throw new ApiException(ErrorCode.BAD_REQUEST, ErrorMessage.of("Bad request. "), ex);
            }
            throw new ApiException(ErrorCode.SYSTEM_ERROR, ErrorMessage.of("System Error. "), ex);
        } catch (Exception e) {
            throw new ApiException(ErrorCode.SYSTEM_ERROR, ErrorMessage.of("System Error. "), e);
        }
    }
}
