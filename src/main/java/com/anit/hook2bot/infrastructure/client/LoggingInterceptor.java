package com.anit.hook2bot.infrastructure.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest req, byte[] reqBody, ClientHttpRequestExecution ex) throws IOException {
        ClientHttpResponse response = ex.execute(req, reqBody);
        if(log.isDebugEnabled()) {
            log.debug("Request url: {}, body: {}", req.getURI(), new String(reqBody, StandardCharsets.UTF_8));
            InputStreamReader isr = new InputStreamReader(response.getBody(), StandardCharsets.UTF_8);
            String body = new BufferedReader(isr)
                    .lines()
                    .collect(Collectors.joining("\n"));
            log.info("Response body: {}", body);
        }
        return response;
    }
}